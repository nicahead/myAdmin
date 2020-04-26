package me.nic.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机构表
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_office")
@ApiModel(value="Office对象", description="机构表")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级编号")
    private Long pid;

    @ApiModelProperty(value = "所有父级编号")
    private String pids;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private BigDecimal sort;

    @ApiModelProperty(value = "归属区域")
    private Long areaId;

    @ApiModelProperty(value = "区域编码")
    private String code;

    @ApiModelProperty(value = "机构类型")
    private String type;

    @ApiModelProperty(value = "机构等级")
    private String grade;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "负责人")
    private String master;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否启用")
    @TableField("USEABLE")
    private Boolean useable;

    @ApiModelProperty(value = "主负责人")
    @TableField("PRIMARY_PERSON")
    private Long primaryPerson;

    @ApiModelProperty(value = "副负责人")
    @TableField("DEPUTY_PERSON")
    private Long deputyPerson;

    @ApiModelProperty(value = "创建者")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;


}
