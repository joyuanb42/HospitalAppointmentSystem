package zyb.design.ssm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import zyb.design.ssm.config.SpringConfig;
import zyb.design.ssm.config.SpringMvcConfig;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.entity.UserInfo;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringMvcConfig.class})
@WebAppConfiguration
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUsername(){
        UserInfo userInfo = userService.findByUserName("zhangsan");
        System.out.println("这里");
        System.out.println(userInfo);
        System.out.println(userInfo.getRoles());
        System.out.println("这里");
    }

    @Test
    public void testChildren() {
        UserInfo userInfo = userService.findByUserName("zhangsan");
        System.out.println(userInfo);
        List<TreeNode> treeNodes = userInfo.getTreeNodes();
        System.out.println("这里");
        System.out.println(userService.processPermissions(treeNodes));
        System.out.println("这里");
    }
}
