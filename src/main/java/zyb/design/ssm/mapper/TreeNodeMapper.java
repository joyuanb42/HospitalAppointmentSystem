package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import zyb.design.ssm.entity.TreeNode;

import java.util.List;

@Mapper
public interface TreeNodeMapper extends BaseMapper<TreeNode> {
}
