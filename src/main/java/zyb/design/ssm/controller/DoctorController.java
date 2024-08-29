package zyb.design.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.entity.Doctor;
import zyb.design.ssm.entity.PageResult;
import zyb.design.ssm.service.AppointmentService;
import zyb.design.ssm.service.DoctorService;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("toAddDoctor")
    public String addDoctor() {
        return "modules/oper/addDoctor";
    }
    @RequestMapping("toEditDoctor")
    public String editDoctor() {
        return "modules/oper/editDoctor";
    }
    @RequestMapping("toShowDoctor")
    public String showDoctor() {
        return "modules/oper/showDoctor";
    }

    // 分页查询医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/selectDoctorByPage")
    @ResponseBody
    public PageResult<Doctor> selectDoctorByPage(
            @RequestParam(value = "doctorID",required = false) Integer doctorID,
            @RequestParam(value = "doctorName",required = false) String doctorName,
            @RequestParam(value = "doctorSpeciality",required = false) String doctorSpecialty,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Doctor> doctors = doctorService.findByDoctor(doctorID,doctorName,doctorSpecialty,pageNum,pageSize);
        PageResult<Doctor> result = new PageResult<>();
        result.setList(doctors);
        result.setPageNum(doctors.getPageNum());
        result.setPages(doctors.getPages());
        return result;
    }

    // 添加医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("addDoctor")
    public String addDoctor(Doctor doctor, Model model) {
        boolean k = doctorService.save(doctor);
        Appointment appointment = new Appointment();
        appointment.setDoctorID(doctor.getDoctorID());
        appointment.setDoctorName(doctor.getDoctorName());
        boolean k2 = appointmentService.save(appointment);
        if (k) {
            return "modules/oper/showDoctor";
        } else {
            model.addAttribute("errorMessage", "添加医生失败");
            return "modules/oper/showDoctor";
        }
    }

    // 修改医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("editDoctor")
    public String editDoctor(Doctor doctor, Model model) {
        // 检查医生是否存在
        Doctor existingDoctor = doctorService.getById(doctor.getDoctorID());
        if (existingDoctor == null) {
            model.addAttribute("errorMessage", "医生不存在");
            return "modules/oper/showDoctor";
        }
        // 更新医生信息
        UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
        UpdateWrapper<Appointment> updateWrapper2 = new UpdateWrapper<>();
        updateWrapper.eq("doctorID", doctor.getDoctorID());
        updateWrapper.set("doctorName", doctor.getDoctorName());
        updateWrapper.set("doctorAge", doctor.getDoctorAge());
        updateWrapper.set("doctorGender", doctor.getDoctorGender());
        updateWrapper.set("doctorSpecialty", doctor.getDoctorSpecialty());
        updateWrapper.set("doctorPhone", doctor.getDoctorPhone());
        updateWrapper2.eq("doctorID", doctor.getDoctorID());
        updateWrapper2.set("doctorName", doctor.getDoctorName());
        // 添加其他需要更新的字段
        boolean k = doctorService.update(updateWrapper);
        boolean k2 = appointmentService.update(updateWrapper2);
        if (k) {
            return "modules/oper/showDoctor";
        } else {
            model.addAttribute("errorMessage", "修改医生失败");
            return "modules/oper/showDoctor";
        }
    }

    // 删除医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "removeDoctor")
    @ResponseBody
    public String removeDoctor(@RequestBody List<Integer> doctorIDs){
        // 检查医生是否存在
        for (Integer doctorID : doctorIDs) {
            if (doctorService.getById(doctorID) == null) {
                return "医生ID " + doctorID + " 不存在，无法删除";
            }
        }
        // 删除医生
        boolean k = doctorService.removeByIds(doctorIDs);
        if (!k) {
            return "删除医生信息失败";
        }
        // 删除预约
        boolean k2 = appointmentService.removeByIds(doctorIDs);
        if (!k2) {
            return "删除预约信息失败";
        }
        return "删除医生和预约信息成功";
    }

}
