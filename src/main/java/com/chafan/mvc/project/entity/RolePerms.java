package com.chafan.mvc.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chafan
 * @since 2022-06-08
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_perms")
@ApiModel(value="TRolePerms对象", description="")
public class RolePerms implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roleid;

    private Integer permsid;


}
