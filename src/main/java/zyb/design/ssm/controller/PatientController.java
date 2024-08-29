package zyb.design.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.Doctor;
import zyb.design.ssm.entity.PageResult;
import zyb.design.ssm.entity.Patient;
import zyb.design.ssm.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("toAddPatient")
    public String addPatient() {
        return "modules/oper/addPatient";
    }

    @RequestMapping("toEditPatient")
    public String editPatient() {
        return "modules/oper/editPatient";
    }

    @RequestMapping("toShowPatient")
    public String showPatient() {
        return "modules/oper/showPatient";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/selectPatientByPage")
    @ResponseBody
    public PageResult<Patient> selectPatientByPage(
            @RequestParam(value = "patientID",required = false) Integer patientID,
            @RequestParam(value = "patientName",required = false) String patientName,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Patient> patients = patientService.findByPatient(patientID,patientName,pageNum,pageSize);
        PageResult<Patient> result = new PageResult<>();
        result.setList(patients);
        result.setPageNum(patients.getPageNum());
        result.setPages(patients.getPages());
        return result;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("addPatient")
    public String addPatient(Patient patient, Model model) {
        boolean k = patientService.save(patient);
        if (k) {
            return "modules/oper/showPatient";
        } else {
            model.addAttribute("errorMessage", "添加病人失败");
            return "modules/oper/showPatient";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("editPatient")
    public String editPatient(Patient patient, Model model) {
        // 检查医生是否存在
        Patient existingPatient = patientService.getById(patient.getPatientID());
        if (existingPatient == null) {
            model.addAttribute("errorMessage", "病人不存在");
            return "modules/oper/showPatient";
        }
        // 更新医生信息
        UpdateWrapper<Patient> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("patientID", patient.getPatientID());
        updateWrapper.set("patientName", patient.getPatientName());
        updateWrapper.set("patientAge", patient.getPatientAge());
        updateWrapper.set("patientGender", patient.getPatientGender());
        updateWrapper.set("patientPhone", patient.getPatientPhone());
        // 添加其他需要更新的字段
        boolean k = patientService.update(updateWrapper);
        if (k) {
            return "modules/oper/showPatient";
        } else {
            model.addAttribute("errorMessage", "修改医生失败");
            return "modules/oper/showPatient";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "removePatient")
    @ResponseBody
    public String removePatient(@RequestBody List<Integer> patientIDs){
        boolean k = patientService.removeByIds(patientIDs);
        if (k) {
            return "删除病人信息成功";
        } else {
            return "删除病人信息失败";
        }
    }
}
