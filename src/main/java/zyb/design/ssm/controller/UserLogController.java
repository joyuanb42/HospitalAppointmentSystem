package zyb.design.ssm.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.PageResult;
import zyb.design.ssm.entity.SysOperLog;
import zyb.design.ssm.entity.UserLog;
import zyb.design.ssm.service.UserLogService;

@Controller
@RequestMapping("/userLog")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @RequestMapping("toShowUserLog")
    public String showUserLog() {
        return "modules/log/showUserLog";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/selectUserLogByPage")
    @ResponseBody
    public PageResult<UserLog> selectUserLogByPage(
            @RequestParam(value = "userId",required = false) Integer userId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<UserLog> userLogs = userLogService.findByUserLog(userId,pageNum,pageSize);
        PageResult<UserLog> result = new PageResult<>();
        result.setList(userLogs);
        result.setPageNum(userLogs.getPageNum());
        result.setPages(userLogs.getPages());
        return result;
    }
}
