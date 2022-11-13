package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class SysUavRepair extends BaseEntity {
    /**
     * 无人机ID
     */
    private Long uavId;
    /**
     * 无人机维保ID
     */
    private Long uavRepairId;

    /**
     * 提交用户
     */
    private Long userId;

    /**
     * 反馈用户
     */
    private Long feedbackId;

    /**
     * 提交用户名称
     */
    private String uName;

    /**
     * 反馈用户名称
     */
    private String fName;

    /**
     * 无人机编码
     */
    private String uavNo;
    /**
     * 维保编号
     */
    private String repairNo;

    /**
     * 无人机取件地址
     */
    private String repairAddress;

    /**
     * 无人机取件电话号码
     */
    private String phoneNumber;

    /**
     * 维保状态；0待维保；1已维保
     */
    private Integer repairStatus;

    /**
     * 维保反馈
     */
    private String repairFeedback;


    /**
     * 创建时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 反馈时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss")
    private Date feedbackDate;
}
