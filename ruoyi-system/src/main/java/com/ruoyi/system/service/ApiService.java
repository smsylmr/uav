package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.req.FlightData;
import com.ruoyi.system.domain.vo.DroneVO;

import java.util.List;

/**
 * 无人机表(Drone)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface ApiService {

    void flightData(FlightData flightData, List<String> list);

    void realFlightData(FlightData flightData);
}