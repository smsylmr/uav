package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DroneFlightMapper;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.service.IDroneFlightService;

/**
 * 无人机维保记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Service
public class DroneFlightServiceImpl implements IDroneFlightService 
{
    @Autowired
    private DroneFlightMapper droneFlightMapper;

    /**
     * 查询无人机维保记录
     * 
     * @param id 无人机维保记录主键
     * @return 无人机维保记录
     */
    @Override
    public DroneFlight selectDroneFlightById(String id)
    {
        return droneFlightMapper.selectDroneFlightById(id);
    }

    /**
     * 查询无人机维保记录列表
     * 
     * @param droneFlight 无人机维保记录
     * @return 无人机维保记录
     */
    @Override
    public List<DroneFlight> selectDroneFlightList(DroneFlight droneFlight)
    {
        return droneFlightMapper.selectDroneFlightList(droneFlight);
    }

    /**
     * 新增无人机维保记录
     * 
     * @param droneFlight 无人机维保记录
     * @return 结果
     */
    @Override
    public int insertDroneFlight(DroneFlight droneFlight)
    {
        return droneFlightMapper.insertDroneFlight(droneFlight);
    }

    /**
     * 修改无人机维保记录
     * 
     * @param droneFlight 无人机维保记录
     * @return 结果
     */
    @Override
    public int updateDroneFlight(DroneFlight droneFlight)
    {
        return droneFlightMapper.updateDroneFlight(droneFlight);
    }

    /**
     * 批量删除无人机维保记录
     * 
     * @param ids 需要删除的无人机维保记录主键
     * @return 结果
     */
    @Override
    public int deleteDroneFlightByIds(String[] ids)
    {
        return droneFlightMapper.deleteDroneFlightByIds(ids);
    }

    /**
     * 删除无人机维保记录信息
     * 
     * @param id 无人机维保记录主键
     * @return 结果
     */
    @Override
    public int deleteDroneFlightById(String id)
    {
        return droneFlightMapper.deleteDroneFlightById(id);
    }
}
