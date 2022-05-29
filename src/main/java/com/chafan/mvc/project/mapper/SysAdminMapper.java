package com.chafan.mvc.project.mapper;

import com.chafan.mvc.project.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

//   @Delete("delete from sys_admin where userId=(#{id})")
//   int deleteUser(String id);

}
