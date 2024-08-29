package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import zyb.design.ssm.entity.Patient;
import zyb.design.ssm.entity.UserLog;

public interface UserLogService extends IService<UserLog> {
    Page<UserLog> findByUserLog(@Param("userID") Integer userID,
                                Integer pageNum, Integer pageSize);
}
