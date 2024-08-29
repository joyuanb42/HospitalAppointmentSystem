package zyb.design.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserLog {
    @TableId(value = "userLogId",type = IdType.AUTO)
    private Integer userLogId;
    @TableField("userId")
    private Integer userId;
    @TableField("loginTime")
    private String loginTime;
}
