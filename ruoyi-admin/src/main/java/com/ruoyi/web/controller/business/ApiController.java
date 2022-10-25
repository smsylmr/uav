package com.ruoyi.web.controller.business;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.UnZipUtils;
import com.ruoyi.common.utils.UnzipFileVo;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneFlightData;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.req.FlightData;
import com.ruoyi.system.domain.vo.DroneVO;
import com.ruoyi.system.service.DroneFlightDataService;
import com.ruoyi.system.service.DroneFlightService;
import com.ruoyi.system.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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


    /**
     *
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件飞行数据")
    @Anonymous
    public AjaxResult uploadFile(@RequestPart("file") MultipartFile file,
                                 @RequestPart("data") FlightData flightData) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            FileUploadUtils.upload(filePath, file);
            QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("serial_number",flightData.getSN());
            Drone drone = droneService.getOne(queryWrapper);
            List<UnzipFileVo> unzip = UnZipUtils.unzip(file);
            log.info(JSON.toJSONString(unzip.get(0).getContent()));
//            DroneFlightData droneFlightData = JSON.parseObject(unzip.get(0).getContent(), DroneFlightData.class);
//            droneFlightData.setDroneId(drone.getId());
//            droneFlightDataService.save(droneFlightData);
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
        Drone add = null;
        QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("serial_number",flightData.getSN());
        Drone drone = droneService.getOne(queryWrapper);
        if(null==drone){
            add = new Drone();
            add.setModel(flightData.getModel());
            add.setPolicyNo(flightData.getPolicy());
            add.setPurchasingDate(flightData.getTime());
            add.setSerialNumber(flightData.getSN());
            add.setDeviceStatus(0);
            add.setNetworkStatus(0);
            add.setOnlineStatus(0);
            add.setDelFlag(0);
            droneService.save(add);
        }
        DroneFlightData droneFlightData = OrikaUtils.map(flightData, DroneFlightData.class);
        droneFlightData.setDroneId(drone==null?add.getId():drone.getId());
        droneFlightDataService.save(droneFlightData);
        if(flightData.getIsEnd()){
            DroneFlight droneFlight = new DroneFlight();
            droneFlight.setDroneId(drone==null?add.getId():drone.getId());
            droneFlight.setFlightConsume(flightData.getFlyTime());
            droneFlight.setFlightTime(flightData.getStartTime());
            droneFlight.setLastOnlineTime(LocalDateTime.now());
            droneFlightService.save(droneFlight);
        }
        return AjaxResult.success();
    }
}
