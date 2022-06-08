package com.chafan.mvc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chafan.mvc.project.entity.PushMessageInfo;
import com.chafan.mvc.project.mapper.PushMessageInfoMapper;
import com.chafan.mvc.project.service.IPushMessageInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chafan.mvc.utils.TimeUtils;
import lombok.experimental.var;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.awt.SystemColor.info;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Service
@CacheConfig(cacheNames = "push_message_cache")
public class PushMessageInfoServiceImpl extends ServiceImpl<PushMessageInfoMapper, PushMessageInfo> implements IPushMessageInfoService {

    TimeUtils timeUtils = new TimeUtils();

    /**
     * 根据日期查找每天和、每月或每年的推送消息数量
     * @param pushDate
     * @return
     */
    @Override
    @Cacheable(cacheNames = "push_message_cache")
    public int countMonthMessage(String pushDate) {
        QueryWrapper query = new QueryWrapper();
        query.likeRight("pushDate",pushDate);
        return baseMapper.selectList(query).size();
    }

    /**
     * 计算班级推送消息成功数量，去重后
     * @param personNo
     * @param
     * @return
     */
    @Override
    @Cacheable(cacheNames = "push_message_cache")
    public int countClassMessage(String personNo) {
        QueryWrapper query = new QueryWrapper();
        if(personNo != "" && personNo != null){
            query.likeRight("personNo",personNo);
        }
        if(timeUtils.xxxx_xx_xx() != "" && timeUtils.xxxx_xx_xx() != null){
//            query.likeRight("pushDate",timeUtils.xxxx_xx_xx());
            query.likeRight("pushDate","2022-04-18");
        }
        Set<Object> set = new HashSet<Object>();
        List<PushMessageInfo> list = new ArrayList<PushMessageInfo>();

        for(Object info :baseMapper.selectList(query)){
            list.add((PushMessageInfo)info);
        }
        for(PushMessageInfo info :list){
            set.add(info.getPersonNo()+info.getAfterSchool());
        }
        return set.size();
    }






}
