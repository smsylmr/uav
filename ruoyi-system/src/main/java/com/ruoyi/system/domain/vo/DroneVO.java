package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @description
 * @date 2022/9/2 14:39
 */
@Data
public class DroneVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 无人机id
     */
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
    private String networkStatus;
    /**
     * 在线状态（0在线 1离线）
     */
    private String onlineStatus;
    /**
     * 设备状态（0状态好 1状态良 2状态差 3维保中 4已报废）
     */
    private String deviceStatus;
    /**
     * 购买时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchasingDate;

}
