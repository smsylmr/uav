package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.req.DroneFlightRequest;
import com.ruoyi.system.domain.vo.DroneData;
import com.ruoyi.system.domain.vo.DroneFlightVO;

/**
 * 无人机表(Drone)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface DroneFlightService extends IService<DroneFlight> {

    DroneData getFlightData(String droneId);

    PageUtil<DroneFlightVO> list(DroneFlightRequest droneFlightRequest);
}