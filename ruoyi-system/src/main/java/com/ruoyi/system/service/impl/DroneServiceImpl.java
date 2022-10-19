package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.vo.DroneVO;
import com.ruoyi.system.mapper.DroneMapper;
import com.ruoyi.system.service.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneService")
public class DroneServiceImpl extends ServiceImpl<DroneMapper, Drone> implements DroneService {


    @Override
    public PageUtil<DroneVO> list(DroneRequest droneRequest) {
        QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(droneRequest.getModel())){
            queryWrapper.eq("model",droneRequest.getModel());
        }
        if(StringUtils.isNotEmpty(droneRequest.getSerialNumber())){
            queryWrapper.eq("serial_number",droneRequest.getSerialNumber());
        }
        if(StringUtils.isNotEmpty(droneRequest.getDeviceStatus())){
            queryWrapper.eq("device_status",Integer.parseInt(droneRequest.getDeviceStatus()));
        }
        if(StringUtils.isNotEmpty(droneRequest.getNetworkStatus())){
            queryWrapper.eq("network_status",Integer.parseInt(droneRequest.getNetworkStatus()));
        }
        if(StringUtils.isNotEmpty(droneRequest.getOnlineStatus())){
            queryWrapper.eq("online_status",Integer.parseInt(droneRequest.getOnlineStatus()));
        }
        queryWrapper.eq("del_flag",0);
        Page<Drone> page = page(new Page<>(droneRequest.getCurPage(), droneRequest.getLimit()), queryWrapper);
        List<DroneVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneVO.class);
        return new PageUtil<>(sysDeptVOS,page);
    }

    @Override
    public void del(String droneId) {
        Drone byId = this.getById(droneId);
        byId.setDelFlag(1);
        this.updateById(byId);
    }

    @Override
    public PageUtil<DroneVO> list4Mini(DroneRequest droneRequest) {

        IPage<Drone> byUserId = getBaseMapper().listByUserId(new Page<>(droneRequest.getCurPage(), droneRequest.getLimit()), droneRequest.getUserId());
        List<DroneVO> droneVOList = OrikaUtils.mapAsList(byUserId.getRecords(), DroneVO.class);
        return new PageUtil<>(droneVOList,byUserId);
    }

}