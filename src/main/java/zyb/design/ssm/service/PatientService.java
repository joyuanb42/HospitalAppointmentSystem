package zyb.design.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import zyb.design.ssm.entity.Patient;

public interface PatientService extends IService<Patient> {
    Page<Patient> findByPatient(@Param("patientID") Integer patientID,
                              @Param("patientName") String patientName,
                              Integer pageNum, Integer pageSize);
}
