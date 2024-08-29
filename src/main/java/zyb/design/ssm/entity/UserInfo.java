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
public class UserInfo {
    private String username;
    private List<Integer> roles;
    private List<Integer> permission;
    private List<TreeNode> treeNodes;
}
