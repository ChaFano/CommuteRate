package com.chafan.mvc.project.service.impl;

import com.chafan.mvc.project.entity.UserRole;
import com.chafan.mvc.project.mapper.TUserRoleMapper;
import com.chafan.mvc.project.service.ITUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chafan
 * @since 2022-06-08
 */
@Service
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, UserRole> implements ITUserRoleService {

}
