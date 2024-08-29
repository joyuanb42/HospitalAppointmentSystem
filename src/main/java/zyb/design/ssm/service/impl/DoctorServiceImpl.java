package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.entity.Doctor;
import zyb.design.ssm.mapper.DoctorMapper;
import zyb.design.ssm.service.DoctorService;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Page<Doctor> findByDoctor(Integer doctorID, String doctorName, String doctorSpecialty,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return doctorMapper.selectDoctorByPage(doctorID,doctorName,doctorSpecialty);
    }
}
