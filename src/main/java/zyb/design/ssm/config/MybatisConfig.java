package zyb.design.ssm.config;

//import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

public class MybatisConfig {

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, WebApplicationContext webApplicationContext){
        //配置MyBatis的SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("zyb.design.ssm.entity");

        //设置mybatis-plus分页插件
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        //创建PageInterceptor对象
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        //自动识别数据库类型
        properties.setProperty("auto-dialect", "true");
        //分页参数合理化
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{mybatisPlusInterceptor, pageInterceptor});

        return sqlSessionFactoryBean;
    }

    //配置MyBatis的Mapper接口扫描
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("zyb.design.ssm.mapper");
        return scannerConfigurer;
    }

}
