package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import zyb.design.ssm.entity.SysOperLog;

public interface SysOperLogService extends IService<SysOperLog> {
    Page<SysOperLog> findBySysOperLog(@Param("operMethod") String operMethod,
                                      @Param("operType") String operType,
                                      Integer pageNum, Integer pageSize);
}
