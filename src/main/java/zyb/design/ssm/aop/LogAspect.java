package zyb.design.ssm.aop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zyb.design.ssm.entity.Result;
import zyb.design.ssm.entity.SysOperLog;
import zyb.design.ssm.entity.User;
import zyb.design.ssm.entity.UserLog;
import zyb.design.ssm.service.SysOperLogService;
import zyb.design.ssm.service.UserLogService;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private UserLogService userLogService;
    @Autowired
    private SysOperLogService sysOperLogService;

    private HttpServletRequest request;
    public LogAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("execution(* zyb.design.ssm.service.*.*(..))")
    public void servicePointcut() {
    }

    @Pointcut("execution(* zyb.design.ssm.controller.LoginController.login(..))")
    public void loginPointcut() {
    }

    @AfterReturning(value = "loginPointcut()", returning = "result")
    public void afterLogin(JoinPoint joinPoint, Object result) {
        if (result instanceof Result && ((Result) result).isSuccess()) {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            UserLog userLog = new UserLog();
            userLog.setUserId(user.getId());
            userLog.setLoginTime(String.valueOf(new Date()));
            userLogService.save(userLog);
        }
    }



    public void SysOperLogAspect(SysOperLogService sysOperLogService, HttpServletRequest request) {
        this.sysOperLogService = sysOperLogService;
        this.request = request;
    }


    @Pointcut("execution(* zyb.design.ssm.controller.*.*(..)) && !execution(* zyb.design.ssm.controller.LoginController.*(..))")
    public void controllerPointcut() {
    }

    @After("controllerPointcut()")
    public void afterController(JoinPoint joinPoint) {

        //
        SysOperLog sysOperLog = new SysOperLog();
        //
        sysOperLog.setOperModule(joinPoint.getTarget().getClass().getSimpleName());
        //
        sysOperLog.setOperMethod(joinPoint.getSignature().getName());
        //
        String methodName = joinPoint.getSignature().getName();
        String operType = "";
        if (methodName.contains("add")) {
            operType = "ADD";
        } else if (methodName.contains("edit")) {
            operType = "EDIT";
        } else if (methodName.contains("remove")) {
            operType = "REMOVE";
        } else if (methodName.contains("show") || methodName.contains("select")) {
            operType = "SELECT";
        }
        sysOperLog.setOperType(operType);
        //
        String operDesc = "在 " + joinPoint.getTarget().getClass().getSimpleName() + " 模块执行了 " + operType + " 操作";
        sysOperLog.setOperDesc(operDesc);
        //
        sysOperLog.setReqMethod(request.getMethod());
        //
        sysOperLog.setOperParam(joinPoint.getArgs().toString());
        //
        sysOperLog.setOperIp(request.getRemoteAddr());
        //
        sysOperLog.setOperUri(request.getRequestURI());
        //
        Subject subject = SecurityUtils.getSubject();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            sysOperLog.setOperUserd(user.getUsername());
        }else {
            sysOperLog.setOperUserd("TODO");
        }
        //
        sysOperLog.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        sysOperLogService.save(sysOperLog);
    }
}
