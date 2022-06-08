package com.chafan.mvc.config.swagger;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;


/**
 * @Auther: 茶凡
 * @ClassName SwaggerPrintConfig
 * @Description TODO
 * @date 2022/1/7 13:25
 * @Version 1.0
 */
@Component
@Slf4j
public class SwaggerPrintConfig  implements ApplicationListener<WebServerInitializedEvent> {


    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            // 获取ip
            String host = Inet4Address.getLocalHost().getHostAddress();
            //获取端口号
            int port = event.getWebServer().getPort();
            //获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            log.info("项目启动启动成功！接口文档地址: http://"+host+":"+port+applicationName+"/swagger-ui.html");
            log.info("系统登录地址: http://localhost"+":"+port+applicationName+"/login");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
