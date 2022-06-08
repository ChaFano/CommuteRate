package com.chafan.mvc.project.mapper;

import com.chafan.mvc.project.entity.Perms;
import com.chafan.mvc.project.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {



    //根据⽤⼾id查询所有⻆⾊
    SysAdmin findRolesByUserId(String id);

    //根据⻆⾊id查询权限集合
    List<Perms> findPermsByRoleId(String id);

}
