package com.chafan.mvc.project.controller;



import com.chafan.mvc.utils.R;
import io.swagger.annotations.Api;
import com.chafan.mvc.project.service.IParentService;
import io.swagger.annotations.ApiOperation;
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
@Api(tags="系统用户信息表")
@RestController
@RequestMapping("//parent")
public class ParentController {

    @Autowired
    IParentService parentService;


    @ApiOperation("获取用户信息")
    @GetMapping("/getParentList")
    public R getParentList(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("data",parentService.getParentList());
        return R.ok(map);
    }

    @ApiOperation("添加新用户")
    @PostMapping("/AddParent")
    public R AddParent(String studentName, String personNo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        parentService.AddParent(studentName, personNo);
        return R.ok(map);
    }

    @ApiOperation("删除 用户")
    @PostMapping("/deleteParent")
    public R deleteParent(String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        parentService.deleteParent(id);
        return R.ok(map);
    }


}
