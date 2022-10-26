package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linmr
 * @description:
 * @date 2022/10/26
 */
@Data
@TableName(value = "t_uav")
public class Uav implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * uav_id
     */
    private Integer uavId;

    /**
     * 无人机序列号
     */
    private String uavNo;

    /**
     * 所属区域
     */
    private Integer areaId;

    /**
     * 无人机昵称
     */
    private String uavName;

    /**
     * 无人机型号
     */
    private Integer uavTypeId;

    /**
     * 入网状态；1已入网；0未入网
     */
    private Integer netStatus;

    /**
     * 在线状态；1在线；0离线
     */
    private Integer onlineStatus;

    /**
     * 设备状态；0状态好；1状态良；2状态差；3维保中；4已报废
     */
    private Integer deviceStatus;

    /**
     * 保单号
     */
    private String chitNo;

    /**
     * 购买日期
     */
    private Date buyDate;

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
    private Date lastTime;

    /**
     * 飞行架次
     */
    private Integer flys;

    /**
     * 维保架次
     */
    private Integer repairs;

    /**
     * 当前位置
     */
    private String locations;

    /**
     * 飞行记录
     */
    private String flyCounts;

    /**
     * 维保记录
     */
    private String repairCounts;

    /**
     * 最后定位
     */
    private String lastLocation;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createDate;

}
