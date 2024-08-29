package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.*;
import zyb.design.ssm.mapper.*;
import zyb.design.ssm.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private TreeNodeMapper treeNodeMapper;
    @Autowired
    private RoleTreeNodeMapper roleTreeNodeMapper;

    @Override
    public UserInfo findByUserName(String username) {
        //根据用户名查询用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(userWrapper);
        if (user == null) {
            return null;
        }
        //根据用户id查询用户角色
        int userId = user.getId();
        LambdaQueryWrapper<UserRole> userRoleWrapper = new LambdaQueryWrapper<>();
        userRoleWrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);
        //从用户角色中获取角色id
        List<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(roleIds);
        //根据角色id查询权限id
        List<Integer> treeNodeIds = roleIds.stream()
                .flatMap(roleId -> {
                    LambdaQueryWrapper<RoleTreeNode> roleTreeNodeWrapper = new LambdaQueryWrapper<>();
                    roleTreeNodeWrapper.eq(RoleTreeNode::getRoleId, roleId);
                    return roleTreeNodeMapper.selectList(roleTreeNodeWrapper).stream();
                })
                .map(RoleTreeNode::getTreeNodeId)
                .collect(Collectors.toList());
        //去重
        List<Integer> newTreeNodes = treeNodeIds.stream().distinct().collect(Collectors.toList());
        userInfo.setPermission(newTreeNodes);
        List<TreeNode> treeNodes = newTreeNodes.stream()
                .map(treeNodeId -> {
                    LambdaQueryWrapper<TreeNode> treeNodeWrapper = new LambdaQueryWrapper<>();
                    treeNodeWrapper.eq(TreeNode::getId, treeNodeId);
                    return treeNodeMapper.selectOne(treeNodeWrapper);
                })
                .collect(Collectors.toList());
        userInfo.setTreeNodes(treeNodes);
        return userInfo;
    }

    //转换权限树
    @Override
    public List<TreeNode> processPermissions(List<TreeNode> permissions) {
        List<TreeNode> result = new ArrayList<>();
        for (TreeNode permission : permissions) {
            if (permission.getPId() == 0) {
                result.add(permission);
            }
        }
        for (TreeNode parent : result) {
            setChildren(parent, permissions);
        }
        return result;
    }

    //递归设置子节点
    private void setChildren(TreeNode parent, List<TreeNode> permissions) {
        List<TreeNode> children = new ArrayList<>();
        for (TreeNode permission : permissions) {
            if (permission.getPId().equals(parent.getId())) {
                children.add(permission);
                setChildren(permission, permissions);
            }
        }
        parent.setChildren(children);
    }
}
