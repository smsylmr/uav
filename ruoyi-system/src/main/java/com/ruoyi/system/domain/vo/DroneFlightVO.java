package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linmr
 * @description:
 * @date 2022/9/3
 */
@Data
public class DroneFlightVO {
    /**
     * id
     */
    private String id;
    /**
     * 无人机id
     */
    private String droneId;
    /**
     * 本次飞行里程
     */
    private String flightMileage;
    /**
     * 本次飞行耗时
     */
    private String flightConsume;
    /**
     * 飞行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime flightTime;
    /**
     * 最后在线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastOnlineTime;
}
