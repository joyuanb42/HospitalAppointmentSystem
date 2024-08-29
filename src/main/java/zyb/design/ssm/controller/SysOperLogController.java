package zyb.design.ssm.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.PageResult;
import zyb.design.ssm.entity.SysOperLog;
import zyb.design.ssm.service.SysOperLogService;

@Controller
@RequestMapping("/sysOperLog")
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    @RequestMapping("toShowSysOperLog")
    public String showSysOperLog() {
        return "modules/log/showSysOperLog";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/selectSysOperLogByPage")
    @ResponseBody
    public PageResult<SysOperLog> selectSysOperLogByPage(
            @RequestParam(value = "operMethod",required = false) String operMethod,
            @RequestParam(value = "operType",required = false) String operType,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            Page<SysOperLog> sysOperLogs = sysOperLogService.findBySysOperLog(operMethod,operType,pageNum,pageSize);
            PageResult<SysOperLog> result = new PageResult<>();
            result.setList(sysOperLogs);
            result.setPageNum(sysOperLogs.getPageNum());
            result.setPages(sysOperLogs.getPages());
            return result;
        } catch (Exception e) {
            // 在这里记录异常信息
            e.printStackTrace();
            return null;
        }
    }
}
