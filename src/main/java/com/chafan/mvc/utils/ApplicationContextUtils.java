package com.chafan.mvc.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * @Auther: 茶凡
 * @ClassName ApplicationContextUtils ⼯具类
 * @Description TODO
 * @date 2022/5/31 22:54
 * @Version 1.0
 */
@Component
@Configuration
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override // 这⾥根据注⼊情况添加
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    // 根据bean的名字获取容器中的bean
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
