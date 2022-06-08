package com.chafan.mvc.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-05-15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("push_message_info")
@ApiModel(value="PushMessageInfo对象", description="")
public class PushMessageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流水ID(主键)")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "经过校门")
    @TableField("afterSchool")
    private String afterSchool;

    @ApiModelProperty(value = "推送消息时间")
    @TableField("pushDate")
    private String pushDate;

    @ApiModelProperty(value = "学号")
    @TableField("personNo")
    private String personNo;


}
