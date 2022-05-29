package com.chafan.mvc.project.service;

import com.chafan.mvc.project.entity.PushMessageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
public interface IPushMessageInfoService extends IService<PushMessageInfo> {

   int  countMonthMessage(String pushDate);

   int countClassMessage(String personNo);
}
