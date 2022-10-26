package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.DroneFlightData;
import com.ruoyi.system.domain.Uav;
import com.ruoyi.system.mapper.DroneFlightDataMapper;
import com.ruoyi.system.mapper.UavMapper;
import com.ruoyi.system.service.DroneFlightDataService;
import com.ruoyi.system.service.UavService;
import org.springframework.stereotype.Service;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("uavService")
public class UavServiceImpl extends ServiceImpl<UavMapper, Uav> implements UavService {

}