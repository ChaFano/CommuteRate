package com.chafan.mvc.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_admin")
@ApiModel(value="SysAdmin对象", description="")
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "登录账号")
    @TableId("user_id")
    private String userId;

    @ApiModelProperty(value = "管理员姓名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime datetime;

    @ApiModelProperty(value = "盐")
    private String salt;

    // 定义权限集合
    private List<Role> roles;


}
