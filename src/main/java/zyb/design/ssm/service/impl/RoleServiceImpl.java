package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.Role;
import zyb.design.ssm.mapper.RoleMapper;
import zyb.design.ssm.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
