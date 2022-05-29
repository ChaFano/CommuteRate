package com.chafan.mvc.project.controller;


import com.chafan.mvc.project.entity.vo.Commute;
import com.chafan.mvc.project.service.IPushMessageInfoService;
import com.chafan.mvc.utils.JsonFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Api(tags="消息推送记录模块")
@RestController
@RequestMapping("//push-message-info")
@EnableScheduling
public class PushMessageInfoController {

    @Autowired
    IPushMessageInfoService pushMessageInfoService;

    @ApiOperation("获取每月或每天或每年推送消息记录条数")
    @GetMapping("/countMonthMessage")
    public int countMonthMessage(String pushDate){
        return pushMessageInfoService.countMonthMessage(pushDate);
    }

    @ApiOperation("获取班级或年级推送消息记录条数")
    @GetMapping("/countClassMessage")
    public int countClassMessage(String personNo) {
        return pushMessageInfoService.countClassMessage(personNo);
    }

    @ApiOperation("通勤率统计")
    @GetMapping("/commute")
    public List<Commute> commute() {

        String [][] personNo = {
                { "201601","201602","201603","201604","201605","201606"},
                { "201701","201702","201703","201704","201705","201706"},
                { "201801","201802","201803","201804","201805","201806"},
                { "201901","201902","201903","201904","201905","201906"},
                { "202001","202002","202003","202004","202005","202006"},
                { "202101","202102","202103","202104","202105","202106"}
        };

        List<Commute> list2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Commute commute = new Commute();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                commute.setName( i+1 + "班");
                list.add(pushMessageInfoService.countClassMessage(personNo[i][j]));
            }
            commute.setData(list);
            list2.add(commute);

        }
        return list2;
    }

    @ApiOperation("年级通勤率统计")
    @GetMapping("/commuteGrade")
    public List<Commute> commuteGrade() {

        String [] personNo = { "2016",  "2017", "2018", "2019", "2020", "2021"};
        int [] students = { 220,  257, 280, 302, 298, 315};

        List<Commute> list2 = new ArrayList<>();
        Commute commute = new Commute();
        Commute commute1 = new Commute();
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(pushMessageInfoService.countClassMessage(personNo[i]));
            list1.add(students[i]*2);
        }
        commute.setName("实际次数");
        commute1.setName("应到次数");
        commute.setData(list);
        commute1.setData(list1);
        list2.add(commute);
        list2.add(commute1);
        return list2;
    }



}
