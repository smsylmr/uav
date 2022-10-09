package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Administrator
 * @description
 * @date 2022/9/2 14:44
 */
@Data
public class DroneData {
    /**
     * 总飞行时间
     */
    private String totalFlightTime;
    /**
     * 距离上一次维保飞行时间
     */
    private String afterLastMaintenanceFlightTime;
    /**
     * 总飞行里程
     */
    private String totalFlightMileage;
    /**
     * 距离上一次维保飞行里程
     */
    private String afterLastMaintenanceFlightMileage;
    /**
     * 最后在线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastOnlineTime;
    /**
     * 飞行架次
     */
    private Integer flights;
    /**
     * 维保架次
     */
    private Integer maintenanceSorties;
}
