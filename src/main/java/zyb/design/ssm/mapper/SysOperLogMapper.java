package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import zyb.design.ssm.entity.Patient;
import zyb.design.ssm.entity.SysOperLog;

@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    @Select({
            "<script>",
            "select * from sys_oper_log",
            "<where>",
            "<if test='operMethod != null'>and oper_method like concat('%', #{operMethod}, '%')</if>",
            "<if test='operType != null'>and oper_type like concat('%', #{operType}, '%')</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "operMethod", column = "oper_method"),
            @Result(property = "operType", column = "oper_type"),
    })
    Page<SysOperLog> selectSysOperLogByPage(@Param("operMethod") String operMethod,
                                            @Param("operType") String operType);
}
