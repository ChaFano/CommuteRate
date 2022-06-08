package com.chafan.mvc.project.service.impl;

import com.chafan.mvc.project.entity.Role;
import com.chafan.mvc.project.mapper.TRoleMapper;
import com.chafan.mvc.project.service.ITRoleService;
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
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, Role> implements ITRoleService {

}
