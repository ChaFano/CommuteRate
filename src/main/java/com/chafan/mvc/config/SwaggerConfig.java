package com.chafan.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: 茶凡
 * @ClassName SwaggerConfig
 * @Description TODO
 * @date 2021/12/30 16:40
 * @Version 1.0
 */

@Configuration //标明是配置类
@EnableSwagger2 //开启swagger功能
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  //DocumentationType.SWAGGER_2 固定的，代表swagger2
                //.groupName("分布式任务系统") //如果配置多个文档的时候，那么需要配置groupName来分组标识
                .apiInfo(apiInfo()) //用于生成API信息
                .select() //select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .apis(RequestHandlerSelectors.basePackage("com.chafan.mvc.project.controller")) //用于指定扫描哪个包下的接口
               // .paths(PathSelectors.regex(".sys/login"))//选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .build();
    }

    /**
     * 用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据分析项目API") //可以用来自定义API的主标题
                .description("数据分析平台SwaggerAPI管理") //可以用来描述整体的API
                .termsOfServiceUrl("") //用于定义服务的域名
                .version("1.0") //可以用来定义版本。
                .build();
    }

    /**
     * swagger-ui.html访问不了，页面报错404,解决办法
     *
     * @param registry 注意：重写 addResourceHandlers 方法需要 implements WebMvcConfigurer 类
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
