package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.entity.User;
import zyb.design.ssm.entity.UserInfo;

import java.util.List;

public interface UserService extends IService<User> {
    UserInfo findByUserName(@Param("username") String username);
    List<TreeNode> processPermissions(List<TreeNode> permissions);
}
