package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneMaintenance;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @date 2022/9/2 14:44
 */
@Data
public class DroneDetails {
    /**
     * 飞行记录
     */
    List<DroneFlight> droneFlightList;
    /**
     * 维保记录
     */
    List<DroneMaintenance> droneMaintenanceList;
}
