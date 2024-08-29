package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zyb.design.ssm.entity.TreeNode;

import java.util.List;

public interface TreeNodeService extends IService<TreeNode> {
    boolean addNode(TreeNode treeNode);
    boolean editNode(TreeNode treeNode);
}
