package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.system.domain.DroneMaintenance;
import com.ruoyi.system.domain.req.DroneMaintenanceRequest;
import com.ruoyi.system.domain.vo.DroneMaintenanceVO;

/**
 * 无人机表(Drone)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface DroneMaintenanceService extends IService<DroneMaintenance> {

    DroneMaintenance getLastMaintenanceByDroneId(String droneId);

    Integer getCountByDroneId(String droneId);

    PageUtil<DroneMaintenanceVO> list(DroneMaintenanceRequest droneMaintenanceRequest);
}