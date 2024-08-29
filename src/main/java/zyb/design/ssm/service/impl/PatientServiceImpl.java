package zyb.design.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyb.design.ssm.entity.Patient;
import zyb.design.ssm.mapper.PatientMapper;
import zyb.design.ssm.service.PatientService;
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Page<Patient> findByPatient(Integer patientID, String patientName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return patientMapper.selectPatientByPage(patientID,patientName);
    }
}
