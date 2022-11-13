package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class SysUavType extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 无人机类型id
     */
    private Long uavTypeId;

    /**
     * 无人机类型名称
     */
    private String uavTypeName;

    /**
     * 无人机类型路径
     */
    private String imgPath;

    /**
     * 无人机类型备注
     */
    private String uavRemark;

    /**
     * 创建时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateDate;
}
