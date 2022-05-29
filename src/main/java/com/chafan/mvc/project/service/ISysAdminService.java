package com.chafan.mvc.project.service;

import com.chafan.mvc.project.entity.SysAdmin;
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
public interface ISysAdminService extends IService<SysAdmin> {

    String addUser(SysAdmin user);

    SysAdmin getPassword(String userId);

    String updateUser(String userId, String oldPassword,String newPassword);

    List<SysAdmin> getListUsers();

    int deleteUser(String userId);

}
