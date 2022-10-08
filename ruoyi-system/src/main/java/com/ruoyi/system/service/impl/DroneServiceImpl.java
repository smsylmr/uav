package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DroneMapper;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.service.IDroneService;

/**
 * 无人机基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Service
public class DroneServiceImpl implements IDroneService 
{
    @Autowired
    private DroneMapper droneMapper;

    /**
     * 查询无人机基本信息
     * 
     * @param id 无人机基本信息主键
     * @return 无人机基本信息
     */
    @Override
    public Drone selectDroneById(String id)
    {
        return droneMapper.selectDroneById(id);
    }

    /**
     * 查询无人机基本信息列表
     * 
     * @param drone 无人机基本信息
     * @return 无人机基本信息
     */
    @Override
    public List<Drone> selectDroneList(Drone drone)
    {
        return droneMapper.selectDroneList(drone);
    }

    /**
     * 新增无人机基本信息
     * 
     * @param drone 无人机基本信息
     * @return 结果
     */
    @Override
    public int insertDrone(Drone drone)
    {
        return droneMapper.insertDrone(drone);
    }

    /**
     * 修改无人机基本信息
     * 
     * @param drone 无人机基本信息
     * @return 结果
     */
    @Override
    public int updateDrone(Drone drone)
    {
        return droneMapper.updateDrone(drone);
    }

    /**
     * 批量删除无人机基本信息
     * 
     * @param ids 需要删除的无人机基本信息主键
     * @return 结果
     */
    @Override
    public int deleteDroneByIds(String[] ids)
    {
        return droneMapper.deleteDroneByIds(ids);
    }

    /**
     * 删除无人机基本信息信息
     * 
     * @param id 无人机基本信息主键
     * @return 结果
     */
    @Override
    public int deleteDroneById(String id)
    {
        return droneMapper.deleteDroneById(id);
    }
}
