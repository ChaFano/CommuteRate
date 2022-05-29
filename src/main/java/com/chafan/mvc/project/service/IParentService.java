package com.chafan.mvc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chafan.mvc.project.entity.Parent;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
public interface IParentService extends IService<Parent> {

    List getParentList();

    int AddParent(String studentName, String personNo);

    int deleteParent(String id);
}
