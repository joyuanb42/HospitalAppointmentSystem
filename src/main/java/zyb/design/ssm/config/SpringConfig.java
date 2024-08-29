package zyb.design.ssm.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(value = "zyb.design.ssm",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})
})
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
//导入jdbc配置文件
@PropertySource("classpath:db.properties")
//导入配置类
@Import({JdbcConfig.class, MybatisConfig.class,ShiroConfig.class,RedisConfig.class})
public class SpringConfig {
}
