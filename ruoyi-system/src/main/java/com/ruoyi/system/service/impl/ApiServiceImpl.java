package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DistanceUtils;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneFlightData;
import com.ruoyi.system.domain.Uav;
import com.ruoyi.system.domain.req.FlightData;
import com.ruoyi.system.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2022/11/13
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {

    @Resource
    private DroneService droneService;
    @Resource
    private DroneFlightDataService droneFlightDataService;
    @Resource
    private DroneFlightService droneFlightService;
    @Resource
    private UavService uavService;

    @Override
    @Transactional
    public void flightData(FlightData flightData,List<String> list) {
        if(list.size()==0){
            throw new RuntimeException("gps数据为空");
        }
        int size = list.size();
        if(size%2!=0){
            throw new RuntimeException("gps数据错误，非偶数条");
        }
        Drone add = null;
        QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("serial_number",flightData.getSn());
        Drone drone = droneService.getOne(queryWrapper);
        if(null==drone){
            add = new Drone();
            add.setModel(flightData.getModel());
            add.setPolicyNo(flightData.getPolicy());
            add.setPurchasingDate(flightData.getTime());
            add.setSerialNumber(flightData.getSn());
            add.setDeviceStatus(0);
            add.setNetworkStatus(0);
            add.setOnlineStatus(0);
            add.setDelFlag(0);
            droneService.save(add);
        }
        List<String> gps = new ArrayList<>();
        List<DroneFlightData> droneFlightDataList = new ArrayList<>();
        for (int i = 0; i < list.size()-1; i+=2) {
            DroneFlightData droneFlightData = OrikaUtils.map(flightData, DroneFlightData.class);
            droneFlightData.setDroneId(drone==null?add.getId():drone.getId());
            droneFlightData.setGps(list.get(i)+list.get(i+1));
            droneFlightDataList.add(droneFlightData);
            gps.add(list.get(i+1));
        }
        droneFlightDataService.saveBatch(droneFlightDataList);
        BigDecimal distance = BigDecimal.ZERO;
        for (int i = 0; i < gps.size()-1; i++) {
            String[] split1 = gps.get(i).split(",");
            String[] split2 = gps.get(i+1).split(",");
            double latitudeFrom = Double.parseDouble(split1[3]);
            double longitudeFrom = Double.parseDouble(split1[5]);
            double latitudeTo = Double.parseDouble(split2[3]);
            double longitudeTo = Double.parseDouble(split2[5]);
            double dis = DistanceUtils.getDistance(longitudeFrom, latitudeFrom, longitudeTo, latitudeTo);
            distance = distance.add(BigDecimal.valueOf(dis));
        }
        DroneFlight droneFlight = new DroneFlight();
        droneFlight.setId(flightData.getFlyNum());
        droneFlight.setDroneId(drone==null?add.getId():drone.getId());
        droneFlight.setFlightMileage(distance.toString());
        droneFlight.setFlightConsume(flightData.getFlyTime());
        droneFlight.setFlightTime(flightData.getStartTime());
        droneFlight.setLastOnlineTime(LocalDateTime.now());
        droneFlightService.save(droneFlight);
    }

    @Override
    public void realFlightData(FlightData flightData) {
        QueryWrapper<Uav> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uav_no",flightData.getSn());
        Uav one = uavService.getOne(queryWrapper);
        if(one==null){
            Uav uav = new Uav();
            uav.setUavNo(flightData.getSn());
            uav.setChitNo(flightData.getPolicy());
            uav.setLocations(flightData.getGps());
            uav.setCreateDate(new Date());
            uavService.save(uav);
        }else {
            one.setLocations(flightData.getGps());
            uavService.updateById(one);
        }
    }
}
