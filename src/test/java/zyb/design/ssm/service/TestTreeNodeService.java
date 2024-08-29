package zyb.design.ssm.service;

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
public class TestTreeNodeService {

    @Autowired
    private TreeNodeService treeNodeService;

    @Test
    public void testGetTreeNodes(){
        System.out.println(treeNodeService.list());
    }
}
