package com.chafan.mvc.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("parent")
@ApiModel(value="ParentInfo对象", description="")
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门编号（班级编号）")
    @TableField("departmentId")
    private Integer departmentId;

    @ApiModelProperty(value = "企业部门根节点ID（年级编号）")
    @TableField("parentDeptId")
    private Integer parentDeptId;

    @ApiModelProperty(value = "学生学号")
    @TableId("personNo")
    private String personNo;

    @ApiModelProperty(value = "学生姓名")
    @TableField("studentName")
    private String studentName;




}
