package zyb.design.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.service.TreeNodeService;

import java.util.List;

@Controller
@RequestMapping("/treeNode")
public class TreeNodeController {

    @Autowired
    private TreeNodeService treeNodeService;

    @RequestMapping("/toMenu")
    public String toTreeNodeMean() {
        return "ztree/menu";
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addNode")
    @ResponseBody
    public String addNode(@RequestBody TreeNode newNode) {
        boolean result = treeNodeService.addNode(newNode);
        return result ? "节点添加成功" : "节点添加失败";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/editNode")
    @ResponseBody
    public String editNode(@RequestBody TreeNode treeNode) {
        boolean result = treeNodeService.editNode(treeNode);
        return result ? "节点修改成功" : "节点修改失败";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/deleteNode")
    @ResponseBody
    public String deleteNode(@RequestBody Integer deleteId) {
        boolean result = treeNodeService.removeById(deleteId);
        return result ? "节点删除成功" : "节点删除失败";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("/selectTreeNodes")
    @ResponseBody
    public List<TreeNode> selectTreeNodes() {
        return treeNodeService.list();
    }
}
