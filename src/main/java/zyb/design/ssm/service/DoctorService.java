package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.entity.Doctor;

import java.util.List;

public interface DoctorService extends IService<Doctor> {
    Page<Doctor> findByDoctor(@Param("doctorID") Integer doctorID,
                                @Param("doctorName") String doctorName,
                                @Param("doctorSpecialty") String doctorSpecialty,
                                Integer pageNum, Integer pageSize);
}
