package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.mapper.TreeNodeMapper;
import zyb.design.ssm.service.TreeNodeService;

import java.util.Collections;
import java.util.List;

@Service
public class TreeNodeServiceImpl extends ServiceImpl<TreeNodeMapper, TreeNode> implements TreeNodeService {
    @Override
    public boolean addNode(TreeNode treeNode) {
        return save(treeNode);
    }

    @Override
    public boolean editNode(TreeNode treeNode) {
        return updateById(treeNode);
    }
}
