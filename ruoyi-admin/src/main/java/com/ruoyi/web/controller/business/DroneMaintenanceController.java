package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.DroneMaintenance;
import com.ruoyi.system.domain.req.DroneMaintenanceAdd;
import com.ruoyi.system.domain.req.DroneMaintenanceRequest;
import com.ruoyi.system.domain.vo.DroneMaintenanceVO;
import com.ruoyi.system.service.DroneMaintenanceService;
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

@Api(tags = "无人机维保记录模块")
@RestController
@RequestMapping("/maintenance")
public class DroneMaintenanceController {
    @Resource
    private DroneMaintenanceService droneMaintenanceService;

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @Anonymous
    public AjaxResult add(@RequestBody DroneMaintenanceAdd droneMaintenanceAdd){
        DroneMaintenance droneMaintenance = OrikaUtils.map(droneMaintenanceAdd, DroneMaintenance.class);
        droneMaintenanceService.save(droneMaintenance);
        return AjaxResult.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    @Anonymous
    public AjaxResult list(@RequestBody DroneMaintenanceRequest droneMaintenanceRequest){
        PageUtil<DroneMaintenanceVO> list = droneMaintenanceService.list(droneMaintenanceRequest);
        return AjaxResult.success(list);
    }
}
