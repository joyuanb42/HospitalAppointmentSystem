package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.mapper.AppointmentMapper;
import zyb.design.ssm.service.AppointmentService;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
}
