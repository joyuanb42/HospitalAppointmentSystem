package zyb.design.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.entity.Doctor;
import zyb.design.ssm.entity.Patient;
import zyb.design.ssm.service.AppointmentService;
import zyb.design.ssm.service.DoctorService;
import zyb.design.ssm.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("toAddAppointment")
    public String addAppointment() {
        return "modules/oper/addAppointment";
    }

    @RequestMapping("toEditAppointment")
    public String editAppointment() {
        return "modules/oper/editAppointment";
    }

    @RequestMapping("toShowAppointment")
    public String showAppointment() {
        return "modules/oper/showAppointment";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("addAppointment")
    public String addAppointment(Doctor doctor, Patient patient, Appointment appointment, Model model) {
        // 检查要预约的医生是否存在与预约表
        appointment.setDoctorID(doctor.getDoctorID());
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctorID", appointment.getDoctorID());
        if (appointmentService.count(queryWrapper) == 0) {
            model.addAttribute("errorMessage", "医生不存在");
            return "modules/oper/showAppointment";
        } else {
            // 检查病人是否存在
            Patient existingPatient = patientService.getById(patient.getPatientID());
            if (existingPatient == null) {
                model.addAttribute("errorMessage", "病人不存在");
                return "modules/oper/showAppointment";
            } else {
                // 添加预约信息
                UpdateWrapper<Appointment> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("doctorID", appointment.getDoctorID());
                updateWrapper.set("patientID", appointment.getPatientID());
                updateWrapper.set("patientName", existingPatient.getPatientName());
                updateWrapper.set("patientPhone", existingPatient.getPatientPhone());
                updateWrapper.set("appointmentTime", appointment.getAppointmentTime());
                boolean k = appointmentService.update(updateWrapper);
                if (k) {
                    return "modules/oper/showAppointment";
                } else {
                    model.addAttribute("errorMessage", "预约失败");
                    return "modules/oper/showAppointment";
                }
            }
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("editAppointment")
    public String editAppointment(Appointment appointment, Model model) {
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patientName", appointment.getPatientName());
        queryWrapper.eq("appointmentTime", appointment.getAppointmentTime());
        if (appointmentService.count(queryWrapper) == 0) {
            model.addAttribute("errorMessage", "预约信息不存在");
            return "modules/oper/showAppointment";
        } else {
            // 更新预约信息
            UpdateWrapper<Appointment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("patientName", appointment.getPatientName());
            updateWrapper.eq("appointmentTime", appointment.getAppointmentTime());
            updateWrapper.set("appointmentTime", appointment.getNewAppointmentTime());
            boolean k = appointmentService.update(updateWrapper);
            if (k) {
                return "modules/oper/showAppointment";
            } else {
                model.addAttribute("errorMessage", "修改预约失败");
                return "modules/oper/showAppointment";
            }
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "removeAppointment")
    @ResponseBody
    public String removeAppointment(@RequestBody List<Integer> appointmentIDs){
        for (Integer appointmentID : appointmentIDs) {
            // 获取预约记录
            Appointment appointment = appointmentService.getById(appointmentID);
            if (appointment != null) {
                // 将病人ID、姓名、电话和预约时间置为null
                UpdateWrapper<Appointment> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("appointmentID", appointmentID); // 添加这一行
                updateWrapper.set("patientID", null);
                updateWrapper.set("patientName", null);
                updateWrapper.set("patientPhone", null);
                updateWrapper.set("appointmentTime", null);
                // 更新预约记录
                boolean k = appointmentService.update(updateWrapper);
                if (!k) {
                    return "预约记录取消失败";
                }
            }
        }
        return "预约记录已取消";
    }

}
