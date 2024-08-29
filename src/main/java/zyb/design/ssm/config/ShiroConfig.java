package zyb.design.ssm.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import zyb.design.ssm.realm.UserRealm;

import java.util.HashMap;
import java.util.Map;

public class ShiroConfig {

    //自定义的Realm
    @Bean
    UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    //Shiro的会话管理器
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return sessionManager;
    }

    //Shiro的安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm,DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        // Set the SecurityManager for the static SecurityUtils accessor
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    //Shiro的生命周期管理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //Shiro的过滤器工厂
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/main");
        shiroFilterFactoryBean.setUnauthorizedUrl("/");
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/toTest", "anon");
        filterMap.put("/test", "anon");
        filterMap.put("/regist", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/img/**","anon");
        filterMap.put("/bootstrap/**","anon");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //Shiro的代理创建器
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    //Shiro的授权管理器
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return  authorizationAttributeSourceAdvisor;
    }

}
