package zyb.design.ssm.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import zyb.design.ssm.config.SpringConfig;
import zyb.design.ssm.config.SpringMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringMvcConfig.class})
@WebAppConfiguration
public class TestDoctorMapper {

    @Autowired
    private DoctorMapper doctorMapper;

    @Test
    public void testSelectById() {
        System.out.println(doctorMapper.selectById(1));
    }


}
