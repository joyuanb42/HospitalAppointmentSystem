package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import zyb.design.ssm.entity.Appointment;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
