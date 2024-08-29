package zyb.design.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TreeNode {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("pId")
    private Integer pId;
    @TableField("name")
    private String name;
    @TableField("url")
    private String url;
    @TableField("permission")
    private String permission;
    @TableField("target")
    private String target;
    @TableField("status")
    private Integer status;
    @TableField(exist = false)
    private List<TreeNode> children;
    @TableField(exist = false)
    private Boolean open=true;
    @TableField(exist = false)
    private Boolean checked;
}
