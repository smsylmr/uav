package com.ruoyi.web.controller.business;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.req.DroneFlightAdd;
import com.ruoyi.system.domain.req.DroneFlightRequest;
import com.ruoyi.system.domain.vo.DroneFlightVO;
import com.ruoyi.system.service.DroneFlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@Api(tags = "无人机飞行记录模块")
@RestController
@RequestMapping("/flight")
public class DroneFlightController {

    @Resource
    private DroneFlightService droneFlightService;

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @Anonymous
    public AjaxResult add(@RequestBody DroneFlightAdd droneFlightAdd){
        DroneFlight drone1 = OrikaUtils.map(droneFlightAdd, DroneFlight.class);
        droneFlightService.save(drone1);
        return AjaxResult.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    @Anonymous
    public AjaxResult list(@RequestBody DroneFlightRequest droneFlightRequest){
        PageUtil<DroneFlightVO> list = droneFlightService.list(droneFlightRequest);
        return  AjaxResult.success(list);
    }
}
