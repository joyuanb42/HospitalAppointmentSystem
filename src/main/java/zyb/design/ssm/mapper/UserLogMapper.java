package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import zyb.design.ssm.entity.SysOperLog;
import zyb.design.ssm.entity.UserLog;

@Mapper
public interface UserLogMapper extends BaseMapper<UserLog> {
    @Select({
            "<script>",
            "select * from user_log",
            "<where>",
            "<if test='userId != null'>and userId like concat('%', #{userId}, '%')</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "userId", column = "userId"),
    })
    Page<UserLog> selectUserLogByPage(@Param("userId") Integer userId);
}
