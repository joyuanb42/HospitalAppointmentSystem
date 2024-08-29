package zyb.design.ssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RolePermission {
    private Integer roleId;
    private String roleName;
    private Integer permissionId;
    private String permissionName;
    private List<Integer> treeNodeIds;
}
