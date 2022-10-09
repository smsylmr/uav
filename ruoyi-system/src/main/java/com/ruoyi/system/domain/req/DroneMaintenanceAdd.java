package com.ruoyi.system.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linmr
 * @description:
 * @date 2022/9/3
 */
@Data
public class DroneMaintenanceAdd {
    private String id;
    /**
     * 无人机id
     */
    private String droneId;
    /**
     * 维保时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintenanceTime;
    /**
     * 维保原因
     */
    private String maintenanceReason;
}
