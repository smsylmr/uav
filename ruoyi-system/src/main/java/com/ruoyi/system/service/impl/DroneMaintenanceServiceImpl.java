package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.DroneMaintenance;
import com.ruoyi.system.domain.req.DroneMaintenanceRequest;
import com.ruoyi.system.domain.vo.DroneMaintenanceVO;
import com.ruoyi.system.mapper.DroneMaintenanceMapper;
import com.ruoyi.system.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneMaintenanceService")
public class DroneMaintenanceServiceImpl extends ServiceImpl<DroneMaintenanceMapper, DroneMaintenance> implements DroneMaintenanceService {


    @Override
    public DroneMaintenance getLastMaintenanceByDroneId(String droneId) {
        return this.getBaseMapper().getLastMaintenanceByDroneId(droneId);
    }

    @Override
    public Integer getCountByDroneId(String droneId) {
        return this.getBaseMapper().getCountByDroneId(droneId);
    }

    @Override
    public PageUtil<DroneMaintenanceVO> list(DroneMaintenanceRequest droneMaintenanceRequest) {
        QueryWrapper<DroneMaintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drone_id",droneMaintenanceRequest.getDroneId());
        Page<DroneMaintenance> page = page(new Page<>(droneMaintenanceRequest.getCurPage(), droneMaintenanceRequest.getLimit()), queryWrapper);
        List<DroneMaintenanceVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneMaintenanceVO.class);
        return new PageUtil<>(sysDeptVOS,page);
    }
}