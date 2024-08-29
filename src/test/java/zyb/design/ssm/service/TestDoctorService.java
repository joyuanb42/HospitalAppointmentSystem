package zyb.design.ssm.service;

import com.github.pagehelper.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import zyb.design.ssm.config.SpringConfig;
import zyb.design.ssm.config.SpringMvcConfig;
import zyb.design.ssm.entity.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringMvcConfig.class})
@WebAppConfiguration
public class TestDoctorService {

    @Autowired
    private DoctorService doctorService;

    @Test
    public void testSelectById(){
        System.out.println(doctorService.getById(1));
    }

    @Test
    public void testSelectByPage(){
        Page<Doctor> result = doctorService.findByDoctor(1, "张", "外", 1, 10);
        System.out.println("PageNum: " + result.getPageNum());
        System.out.println("Pages: " + result.getPages());
        System.out.println("Result: " + result);
    }
}
