package com.chafan.mvc.realm;


import com.chafan.mvc.project.entity.SysAdmin;
import com.chafan.mvc.project.service.ISysAdminService;
import com.chafan.mvc.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @Auther: 茶凡
 * @ClassName CustomerRealm
 * @Description TODO
 * @date 2022/5/31 22:50
 * @Version 1.0
 *
 *  自定义 realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    ISysAdminService sysAdminService ;
    /**
     * 权限管理
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
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

        // 从数据库中获取对应的⽤⼾名及密码等信息做认证
        // 1.在⼯⼚中获取service对象

//        try {
//            sysAdminService = (ISysAdminService) ApplicationContextUtils.getBean("iSysAdminService");
//        } catch (Exception e) {
//            System.out.println("==============================================");
//            e.printStackTrace();
//        }

        // 2.根据⾝份信息做查询
        SysAdmin admin = sysAdminService.findByUserId(userId);
        System.out.println(admin.toString());
        // 3.⽤⼾不为空，则返回数据库信息
        if (!ObjectUtils.isEmpty(admin)) {

        // 3.1返回数据库中的信息，底层和⽤⼾输⼊的信息做校验
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
