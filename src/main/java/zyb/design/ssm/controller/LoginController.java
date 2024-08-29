package zyb.design.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.Result;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.entity.User;
import zyb.design.ssm.entity.UserInfo;
import zyb.design.ssm.service.UserService;
import zyb.design.ssm.utils.CaptchaUtil;
import zyb.design.ssm.utils.MD5AndSalt;

import javax.imageio.ImageIO;
import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    //登录
    @RequestMapping("/")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("captcha") String captcha,
                        HttpServletRequest request,
                        Model model,
                        HttpSession session){
        //获取Session中的验证码
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
        //验证码校验
        if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
            return new Result(false, "验证码错误");
        }
        System.out.println("已通过验证码验证");
        // 密码权限校验
        try {
            // 获取当前用户
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            User user=(User)subject.getPrincipal();
            if (user != null) {
                UserInfo userInfo = userService.findByUserName(username);
                if (userInfo != null) {
                    List<TreeNode> treeNodes = userInfo.getTreeNodes();
                    List<TreeNode> newTreeNodes = userService.processPermissions(treeNodes);
                    session.setAttribute("parents", newTreeNodes);
                    session.setAttribute("children", newTreeNodes.get(0).getChildren());
                    model.addAttribute("username", username);
                    session.setAttribute("user", user);
                    System.out.println("登录成功");
                    return new Result(true, "登录成功");
                } else {
                    System.out.println("用户信息未找到");
                    return new Result(false, "用户信息未找到");
                }
            } else {
                System.out.println("登录失败");
                return new Result(false, "登录失败");
            }
        } catch (UnknownAccountException e) {
            System.out.println("登录失败，用户名不存在");
            return new Result(false, "登录失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录失败，密码错误");
            return new Result(false, "登录失败，密码错误");
        } catch (AuthenticationException e) {
            System.out.println("登录失败，用户名或密码错误");
            return new Result(false, "登录失败，用户名或密码错误");
        } catch (Exception e) {
            System.out.println("服务器错误：" + e.getMessage());
            return new Result(false, "服务器错误");
        }
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer captchaCode = new StringBuffer();
        BufferedImage captchaImage = CaptchaUtil.generateCaptchaImage(captchaCode);
        response.setContentType("image/jpeg");
        request.getSession().setAttribute("captcha", captchaCode.toString());
        ImageIO.write(captchaImage, "JPEG", response.getOutputStream());
    }

    //登出
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    //注册
    @RequestMapping("/toRegist")
    public String toRegist(){
        return "regist";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password){
        String salt = MD5AndSalt.generateSalt();
        String encryptedPassword = MD5AndSalt.encryptPassword(password, salt);
        // 把加密后的密码和salt放入user对象中
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        user.setSalt(salt);
        // 将对象存储到数据库中
        userService.save(user);
        return "/login";
    }

    //测试
    @RequestMapping("/toTest")
    public String toTest(){
        return "test";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "ajax测试成功";
    }
}
