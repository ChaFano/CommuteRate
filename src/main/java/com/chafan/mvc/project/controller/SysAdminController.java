package com.chafan.mvc.project.controller;


import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.service.ISysAdminService;
import com.chafan.mvc.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Api(tags = "系统管理员模块")
@RestController
@RequestMapping("//sys-admin")
@CrossOrigin
public class SysAdminController {

    @Autowired
    ISysAdminService sysAdminService;


    @ApiOperation("添加新用户")
    @PostMapping("/addUser")
    public R addUser(SysAdmin user) {
        if (sysAdminService.addUser(user).equals("添加用户成功")){
            return R.ok("200");
        }
        return R.ok("添加用户失败");
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/updateUser")
    public R updateUser(String userId, String oldPassword,String newPassword){
        System.out.println(userId);
        System.out.println(oldPassword);
        System.out.println(newPassword);
        if(sysAdminService.updateUser(userId, oldPassword, newPassword).equals("修改密码成功")){
            return R.ok("修改密码成功");
        }
            return R.ok("修改密码失败");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login( String id, String password){
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            subject.login(new UsernamePasswordToken(id,password));
            map.put("code",1);
            return R.ok(map);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        map.put("code",0);
        return R.ok(map);
    }


    @ApiOperation("获取所有用户")
    @GetMapping("/getUserList")
    public R getUserList(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",0);
        map.put("data",sysAdminService.getListUsers());

        return R.ok(map);
    }

    @ApiOperation("删除用户")
    @GetMapping("/deleteUser")
    public R deleteUser(String id){
        Map<String,Object> map = new HashMap();
        map.put("data",sysAdminService.deleteUser(id));
        return R.ok(map);
    }

}
