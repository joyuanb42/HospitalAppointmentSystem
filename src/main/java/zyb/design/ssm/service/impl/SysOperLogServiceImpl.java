package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.SysOperLog;
import zyb.design.ssm.mapper.SysOperLogMapper;
import zyb.design.ssm.service.SysOperLogService;

@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public Page<SysOperLog> findBySysOperLog(String operMethod, String operType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysOperLogMapper.selectSysOperLogByPage(operMethod, operType);
    }
}
