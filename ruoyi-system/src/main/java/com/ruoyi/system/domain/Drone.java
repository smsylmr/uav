package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 无人机表(Drone)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:39:46
 */
@Data
@TableName(value = "drone")
public class Drone {
    /**
    * 无人机id
    */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
    * 型号
    */
    private String model;
    /**
    * 图片路径
    */
    private String modelPhoto;
    /**
    * 序列号
    */
    private String serialNumber;
    /**
    * 保险单号
    */
    private String policyNo;
    /**
    * 入网状态（0已入网 1未入网）
    */
    private Integer networkStatus;
    /**
    * 在线状态（0在线 1离线）
    */
    private Integer onlineStatus;
    /**
    * 设备状态（0状态好 1状态良 2状态差 3维保中 4已报废）
    */
    private Integer deviceStatus;
    /**
     * 是否删除（0否 1是）
     */
    private Integer delFlag;
    /**
    * 购买时间
    */
    private LocalDateTime purchasingDate;



}