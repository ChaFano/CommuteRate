package com.chafan.mvc.project.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: 茶凡
 * @ClassName IndexController
 * @Description TODO
 * @date 2022/5/12 8:51
 * @Version 1.0
 */
@Api(tags="视图跳转模块")
@Controller
public class MvcController {


    @GetMapping("home")
    public String index(){
        System.out.println("访问 home 页面");
        return "home";
    }

    @GetMapping("gradePage")
    public String gradePage(){
        System.out.println("访问 gradePage 页面");
        return "gradePage";
    }

    @GetMapping("classPage")
    public String classPage(){
        System.out.println("访问 classPage 页面");
        return "classPage";
    }

    @GetMapping("systemHome")
    public String systemHome(){
        System.out.println("访问 systemHome 页面");
        return "systemHome";
    }

    @GetMapping("about")
    public String about(){
        System.out.println("访问 about 页面");
        return "about";
    }

    @GetMapping("admin")
    public String admin(){
        System.out.println("访问 admin 页面");
        return "admin";
    }

    @GetMapping("login")
    public String login(){
        System.out.println("访问 login 页面");
        return "login";
    }

    @GetMapping("loginout")
    public String loginout(){
        System.out.println("访问 login 页面");
        return "login";
    }

    @GetMapping("userform")
    public String userform(){
        System.out.println("访问 userform 页面");
        return "userform";
    }

}
