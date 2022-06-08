package com.chafan.mvc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chafan.mvc.project.entity.Perms;
import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.mapper.SysAdminMapper;
import com.chafan.mvc.project.service.ISysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chafan.mvc.utils.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Slf4j
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    @Autowired
    SysAdminMapper sysAdminMapper;

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
        query.select("password","salt").eq("user_id",userId);
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
            String password = getPassword(userId).getPassword();
            String salt = getPassword(userId).getSalt();
            Md5Hash md5Hash = new Md5Hash(oldPassword,salt,1024);
            if (password.equals(md5Hash.toHex())){
                String salt1 = SaltUtil.getSalt(10);
                Md5Hash md5Hash1 = new Md5Hash(newPassword,salt1,1024);
                admin.setPassword(md5Hash1.toHex());
                admin.setUserId(userId);
                admin.setSalt(salt1);
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
        QueryWrapper query = new QueryWrapper();
        query.select("user_id","username","password","datetime","salt").eq("user_id",userId);
        log.info(baseMapper.selectOne(query).toString());
        return baseMapper.selectOne(query);
    }

    @Override
    public SysAdmin findRolesByUserId(String id) {
        return sysAdminMapper.findRolesByUserId(id);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return sysAdminMapper.findPermsByRoleId(id);
    }


}
