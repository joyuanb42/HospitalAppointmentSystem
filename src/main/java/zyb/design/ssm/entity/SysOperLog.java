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
public class SysOperLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("oper_module")
    private String operModule;
    @TableField("oper_method")
    private String operMethod;
    @TableField("oper_type")
    private String operType;
    @TableField("oper_desc")
    private String operDesc;
    @TableField("req_method")
    private String reqMethod;
    @TableField("oper_param")
    private String operParam;
    @TableField("oper_ip")
    private String operIp;
    @TableField("oper_uri")
    private String operUri;
    @TableField("oper_userd")
    private String operUserd;
    @TableField("create_time")
    private String createTime;
}
