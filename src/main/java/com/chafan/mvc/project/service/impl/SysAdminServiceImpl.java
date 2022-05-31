package com.chafan.mvc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.mapper.SysAdminMapper;
import com.chafan.mvc.project.service.ISysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chafan.mvc.utils.MD5Utils;
import com.chafan.mvc.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    MD5Utils md5Utils = new MD5Utils();

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @Override
    public String addUser(SysAdmin user) {
        String salt = SaltUtil.getSalt(10);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setDatetime(LocalDateTime.now());
        user.setPassword(md5Hash.toHex());
        baseMapper.insert(user);

        return "添加用户成功";
    }

    /**
     * 根据用户名查找密码
     * @param userId
     * @return
     */
    @Override
    public SysAdmin getPassword(String userId) {
        QueryWrapper query = new QueryWrapper();
        query.select("password").eq("user_id",userId);
        return baseMapper.selectOne(query);
    }

    /**
     * 修改用户密码 默认是123456
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public String updateUser(String userId, String oldPassword,String newPassword) {
            SysAdmin admin = new SysAdmin();
        if(md5Utils.getSaltverifyMD5(oldPassword,getPassword(userId).getPassword())){
            admin.setUserId(userId);
            admin.setPassword(md5Utils.getSaltMD5(newPassword));
            admin.setUsername(getPassword(userId).getUsername());
            admin.setDatetime(LocalDateTime.now());
            if (baseMapper.updateById(admin)==1) return "修改密码成功";

        }else{
            return "旧密码不正确";
        }
        return "修改密码成功";
    }

    /**
     * 获取所有管理员用户
     * @return
     */
    @Override
    public List<SysAdmin> getListUsers() {
        QueryWrapper<SysAdmin> query = new QueryWrapper<>();
        query.select("user_id","username","datetime");
        return baseMapper.selectList( query);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public int deleteUser(String userId) {
        return baseMapper.deleteById(userId);
    }

    @Override
    public SysAdmin findByUserId(String userId) {
        System.out.println(baseMapper.selectById(userId));
        return baseMapper.selectById(userId);
    }


}
