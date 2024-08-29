package zyb.design.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RoleTreeNode {
    @TableField("roleId")
    private Integer roleId;
    @TableField("treeNodeId")
    private Integer treeNodeId;
}
