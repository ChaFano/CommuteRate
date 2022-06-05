package com.chafan.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chafan.mvc.project.entity.Parent;
import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.mapper.ParentMapper;
import com.chafan.mvc.project.mapper.SysAdminMapper;
import com.chafan.mvc.project.service.IPushMessageInfoService;
import com.chafan.mvc.project.service.ISysAdminService;

import com.chafan.mvc.utils.RandomName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MvcApplicationTests {

    @Autowired
    ISysAdminService sysAdminService;

    @Autowired
    IPushMessageInfoService pushMessageInfoService;

    @Autowired
    ParentMapper parentMapper;

    @Autowired
    SysAdminMapper sysAdminMapper;


    RandomName name = new RandomName();

    /**
     * 添加用户测试
     */
    @Test
    void contextLoads() {

        SysAdmin user = new SysAdmin();
        user.setUserId("15681026358");
        user.setUsername("胡歌");
        user.setPassword("123456");
        sysAdminService.addUser(user);
    }




    /**
     * 修改密码测试
     */
    @Test
    void updateUserTest(){
        String id = "15681026356";
        String oldPassword = "111111";
        String newPassword = "123456";
        if("修改密码成功".equals(sysAdminService.updateUser(id, oldPassword, newPassword))){
            System.out.println("修改成功");
        }


    }

    /**
     * 统计每个月消息数量
     */
    @Test
    void countMonthTest(){
        pushMessageInfoService.countMonthMessage("2022-04");
    }

    /**
     * 测试生成的时间格式 xxxx_xx_xx
     */
    @Test
    void timerTest(){
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));

    }


    /**
     * 测试 selectByMap()方法
     */
    @Test
    void selectMapTest(){
        /**
         *双主键 使用 selectByMap() 会有问题
         */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username","周一围");
        System.out.println(sysAdminMapper.selectByMap(map));

    }

    @Test
    void randomName(){

        QueryWrapper query = new QueryWrapper();
        query.select("personNo","studentName","departmentId","parentDeptId");
        List<Parent> list = new ArrayList<Parent>();
        list = parentMapper.selectList(query);
        for (Parent item : list) {
            Parent parent = new Parent();
            parent.setPersonNo(item.getPersonNo());
            parent.setStudentName(name.getName());
            parent.setDepartmentId(item.getDepartmentId());
            parent.setParentDeptId(item.getParentDeptId());
            System.out.println(parentMapper.updateById(parent));
        }
    }
}
