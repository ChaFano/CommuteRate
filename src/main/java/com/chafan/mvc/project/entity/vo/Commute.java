package com.chafan.mvc.project.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName Commute
 * @Description TODO
 * @date 2022/5/18 18:48
 * @Version 1.0
 */
@Data
@ApiModel( description="通勤数据")
@ToString
public class Commute {

    @ApiModelProperty(value = "班级")
    private String name;

    @ApiModelProperty(value = "班级通勤数据")
    private List<Integer> data;


}
