package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.UserLog;
import zyb.design.ssm.mapper.UserLogMapper;
import zyb.design.ssm.service.UserLogService;

@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public Page<UserLog> findByUserLog(Integer userID, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userLogMapper.selectUserLogByPage(userID);
    }
}
