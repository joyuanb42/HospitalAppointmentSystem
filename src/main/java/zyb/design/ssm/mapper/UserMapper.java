package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.entity.User;
import zyb.design.ssm.entity.UserInfo;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserInfo selectByName(String username);
    List<TreeNode> processPermissions(List<TreeNode> permissions);
}
