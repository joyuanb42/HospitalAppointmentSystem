package zyb.design.ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zyb.design.ssm.entity.Doctor;
import zyb.design.ssm.service.AppointmentService;
import zyb.design.ssm.service.DoctorService;
import zyb.design.ssm.utils.excel.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/data")
public class ExcelController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    // 导入医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("/importDoctor")
    public String importDoctor(MultipartFile excel) {
        try {
            List<Object> objects = ExcelUtil.readExcel(excel, new Doctor());
            for (Object object : objects) {
                if (object instanceof Doctor) {
                    doctorService.save((Doctor) object);
                }
            }
        } catch (Exception e) {
            System.out.println("-------------------------------------------------------");
            // 记录异常信息
            System.out.println("错误: " + e.getMessage());
            e.printStackTrace();
            // 返回错误信息
            return "错误: " + e.getMessage();
        }
        return "redirect:modules/oper/showDoctor";
    }

    // 导出医生
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping("/exportDoctor")
    public void exportDoctor(HttpServletResponse response) {
        List<Doctor> doctors = doctorService.list();
        ExcelUtil.writeExcel(response, doctors, "doctors", "Doctors", new Doctor());
    }
}
