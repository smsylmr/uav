package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneMaintenance;
import com.ruoyi.system.domain.req.DroneFlightRequest;
import com.ruoyi.system.domain.vo.DroneData;
import com.ruoyi.system.domain.vo.DroneFlightVO;
import com.ruoyi.system.mapper.DroneFlightMapper;
import com.ruoyi.system.service.DroneFlightService;
import com.ruoyi.system.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 无人机飞行表(DroneFlight)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneFlightService")
public class DroneFlightServiceImpl extends ServiceImpl<DroneFlightMapper, DroneFlight> implements DroneFlightService {

    @Resource
    private DroneMaintenanceService droneMaintenanceService;

    @Override
    public DroneData getFlightData(String droneId) {
        DroneData flightData = this.getBaseMapper().getFlightData(droneId);
        DroneMaintenance last = droneMaintenanceService.getLastMaintenanceByDroneId(droneId);
        if(last==null){
            flightData.setAfterLastMaintenanceFlightTime(flightData.getTotalFlightTime());
            flightData.setAfterLastMaintenanceFlightMileage(flightData.getTotalFlightMileage());
        }else{
            DroneData dataAfterLastMaintenance = this.getBaseMapper().getFlightDataAfterLastMaintenance(droneId, last.getMaintenanceTime());
            flightData.setAfterLastMaintenanceFlightMileage(dataAfterLastMaintenance.getAfterLastMaintenanceFlightMileage());
            flightData.setAfterLastMaintenanceFlightTime(dataAfterLastMaintenance.getAfterLastMaintenanceFlightTime());
        }
        Integer countByDroneId = droneMaintenanceService.getCountByDroneId(droneId);
        flightData.setMaintenanceSorties(countByDroneId);
        DroneFlight flight = this.getBaseMapper().getLastFlightByDroneId(droneId);
        if(flight!=null){
            flightData.setLastOnlineTime(flight.getLastOnlineTime());
        }
        return flightData;
    }

    @Override
    public PageUtil<DroneFlightVO> list(DroneFlightRequest droneFlightRequest) {
        QueryWrapper<DroneFlight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drone_id",droneFlightRequest.getDroneId());
        Page<DroneFlight> page = page(new Page<>(droneFlightRequest.getCurPage(), droneFlightRequest.getLimit()), queryWrapper);
        List<DroneFlightVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneFlightVO.class);
        return new PageUtil<>(sysDeptVOS,page);
    }
}