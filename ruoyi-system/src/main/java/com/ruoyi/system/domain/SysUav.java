package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class SysUav extends BaseEntity {
    /**
     * 无人机ID
     */
    private Long uavId;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 入网状态
     */
    private Integer onlineStatus;

    /**
     * 设备状态
     */
    private Integer deviceStatus;

    /**
     * 所属用户名称
     */
    private String nickName;

    /**
     * 无人机编号
     */
    private String uavNo;


    /**
     * 无人机类型ID
     */
    private Long uavTypeId;
    /**
     * 无人机类型名称
     */
    private String uavTypeName;

    /**
     * 无人机名称
     */
    private String uavName;

    /**
     * 无人机类型图片
     */
    private String uavTypeImg;

    /**
     * 无人机所属区域
     */
    private Long areaId;

    /**
     * 无人机所属区域
     */
    private String areaName;

    /**
     * 无人机保单号
     */
    private String chitNo;

    /**
     * 入网状态
     */
    private Integer netStatus;

    /**
     * 创建时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd")
    private Date createDate;

    /**
     * 总飞行时间
     */
    private Float allFly;

    /**
     * 距离上次维保飞行时间
     */
    private Float lastFly;

    /**
     * 总飞行里程
     */
    private Float allFlyLong;

    /**
     * 距离上次维保飞行里程
     */
    private Float lastFlyLong;

    /**
     * 最后在线时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    /**
     * 飞行架次（次数）
     */
    private Long flys;

    /**
     * 维保架次
     */
    private Long repairs;

}
