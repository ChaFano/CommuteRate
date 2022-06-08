package com.chafan.mvc.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.chafan.mvc.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 茶凡
 * @ClassName ShiroConfig
 * @Description TODO
 * @date 2022/5/31 22:40
 * @Version 1.0
 *  整合shiro框架相关的配置类
 *  @apiNote shiroFilter拦截到所有的请求之后，通过web安全管理器对请求进⾏处理，安全管理器需要realm来
 *  决定使⽤什么⽅式来做检验等⼯作，⽐如⽤的是jdbc还是mybatis等等
 */

@Configuration
public class ShiroConfig {

    // 1.创建shiroFilter，负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 1.1配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 1.2配置完安全管理器，你还得告诉拦截器你的系统那些是受限资源哪些是公共资源
        Map<String, String> map = new HashMap<>();
        //map.put("/home.html", "authc");
        map.put("/sys-admin/login","anon");// 登录界面所有都可以访问
        map.put("/sys-admin/getVerify","anon");// 验证码放行
        map.put("/**","authc");// 访问资源，受限资源 authc（过滤器）代表请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 1.3默认认证界⾯路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }

    // 2.创建web安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    // 3.创建⾃定义的realm
    @Bean
    @Primary
    public Realm getRealm(){
        // realm是⽤来定义你的校验⽅式，域对象，设置你的凭证匹配器，加密⽅式，散列次数
        UserRealm userRealm = new UserRealm();
        // 1.设置凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 2.匹配器中使⽤的摘要算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 3.散列的次数
        credentialsMatcher.setHashIterations(1024);

        // 4.开启本地 缓存管理器 还可以使用 redis 做缓存
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setCacheManager(new EhCacheManager());

        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
