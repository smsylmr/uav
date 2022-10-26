package com.ruoyi.web.controller.business;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneFlightData;
import com.ruoyi.system.domain.Uav;
import com.ruoyi.system.domain.req.FlightData;
import com.ruoyi.system.service.DroneFlightDataService;
import com.ruoyi.system.service.DroneFlightService;
import com.ruoyi.system.service.DroneService;
import com.ruoyi.system.service.UavService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Api(tags = "数据传输模块")
@RestController
@RequestMapping("/data")
@Slf4j
public class ApiController
{

    @Resource
    private DroneService droneService;
    @Resource
    private DroneFlightDataService droneFlightDataService;
    @Resource
    private DroneFlightService droneFlightService;
    @Resource
    private UavService uavService;

    /**
     *
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件飞行数据")
    @Anonymous
    public AjaxResult uploadFile(@RequestPart("file") MultipartFile file,
                                 @RequestPart("data") String data)
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String upload = FileUploadUtils.upload(filePath, file);
            String replace = upload.replace("/profile/upload", "");
            String url = filePath + replace;
            List<String> list = FileUtil.readLines(url, "utf-8");
            FlightData flightData = JSON.parseObject(data, FlightData.class);
            log.info(JSON.toJSONString(list));
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
            List<DroneFlightData> droneFlightDataList = new ArrayList<>();
            for (int i = 0; i < list.size()-1; i+=2) {
                DroneFlightData droneFlightData = OrikaUtils.map(flightData, DroneFlightData.class);
                droneFlightData.setDroneId(drone==null?add.getId():drone.getId());
                droneFlightData.setGps(list.get(i)+list.get(i+1));
                droneFlightDataList.add(droneFlightData);
            }
            droneFlightDataService.saveBatch(droneFlightDataList);

            DroneFlight droneFlight = new DroneFlight();
            droneFlight.setId(flightData.getFlyNum());
            droneFlight.setDroneId(drone==null?add.getId():drone.getId());
            droneFlight.setFlightMileage("100");
            droneFlight.setFlightConsume(flightData.getFlyTime());
            droneFlight.setFlightTime(flightData.getStartTime());
            droneFlight.setLastOnlineTime(LocalDateTime.now());
            droneFlightService.save(droneFlight);

            return AjaxResult.success();
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }


    @PostMapping("/realFlightData")
    @ApiOperation(value = "实时飞行数据")
    @Anonymous
    public AjaxResult realFlightData(@RequestBody FlightData flightData)
    {
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
        return AjaxResult.success();
    }
}
