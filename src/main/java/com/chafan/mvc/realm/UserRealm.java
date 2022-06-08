package com.chafan.mvc.realm;


import com.chafan.mvc.project.entity.Perms;
import com.chafan.mvc.project.entity.Role;
import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.service.ISysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName CustomerRealm
 * @Description TODO
 * @date 2022/5/31 22:50
 * @Version 1.0
 *
 *  自定义 realm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    ISysAdminService sysAdminService ;
    /**
     * 权限管理
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取身份信息
        String primaryPrincipals = (String) principals.getPrimaryPrincipal();
        log.info("Principals" + primaryPrincipals);
        // 根据 主身份信息获取角色 和 权限信息
        SysAdmin sysAdmin = sysAdminService.findRolesByUserId(primaryPrincipals);
        // 授权角色
        if(!CollectionUtils.isEmpty(sysAdmin.getRoles())){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            sysAdmin.getRoles().forEach(role ->{
                info.addRole(role.getName());

                // 权限信息
                List<Perms> perms =  sysAdminService.findPermsByRoleId(role.getId().toString());
                if(CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm ->{
                        info.addStringPermission(perm.getName());
                    });
                }
            });
            return info;
        }
        return null;
    }

    /**
     * 认证 主要处理 dao层 跟数据库做数据验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userId = (String) token.getPrincipal();
        // 2.根据身份信息做查询
        SysAdmin admin = sysAdminService.findByUserId(userId);
        System.out.println(admin.toString());
        // 3.⽤⼾不为空，则返回数据库信息
        if (!ObjectUtils.isEmpty(admin)) {

        // 3.1返回数据库中的信息，底层和用户输入的信息做校验
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    admin.getUserId(),
                    admin.getPassword(),
                    ByteSource.Util.bytes(admin.getSalt()),
                    this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

}
