package com.ruoyi.web.controller.business;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.req.DroneAdd;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.vo.DroneData;
import com.ruoyi.system.domain.vo.DroneVO;
import com.ruoyi.system.service.DroneFlightService;
import com.ruoyi.system.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@Api(tags = "无人机基本信息模块")
@RestController
@RequestMapping("/drone")
public class DroneController {

    @Resource
    private DroneService droneService;
    @Resource
    private DroneFlightService droneFlightService;


    @PostMapping("/list")
    @ApiOperation(value = "列表")
    @Anonymous
    public AjaxResult list(@RequestBody DroneRequest droneRequest){
        PageUtil<DroneVO> list = droneService.list(droneRequest);
        return  AjaxResult.success(list);
    }

    @GetMapping("/getFlightData/{droneId}")
    @ApiOperation(value = "详情")
    @Anonymous
    public AjaxResult getFlightData(@PathVariable String droneId){
        DroneData droneData = droneFlightService.getFlightData(droneId);
        return  AjaxResult.success(droneData);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @Anonymous
    public AjaxResult add(@RequestBody DroneAdd drone){
        Drone drone1 = OrikaUtils.map(drone, Drone.class);
        droneService.save(drone1);
        return  AjaxResult.success();
    }

    @DeleteMapping("/del/{droneId}")
    @ApiOperation(value = "删除")
    @Anonymous
    public AjaxResult delete(@PathVariable String droneId){
        droneService.del(droneId);
        return  AjaxResult.success();
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新")
    @Anonymous
    public AjaxResult update(@RequestBody DroneAdd drone){
        Drone drone1 = OrikaUtils.map(drone, Drone.class);
        droneService.updateById(drone1);
        return  AjaxResult.success();
    }

}
