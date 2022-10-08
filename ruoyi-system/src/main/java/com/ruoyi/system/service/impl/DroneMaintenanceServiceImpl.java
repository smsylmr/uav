package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DroneMaintenanceMapper;
import com.ruoyi.system.domain.DroneMaintenance;
import com.ruoyi.system.service.IDroneMaintenanceService;

/**
 * 无人机维保记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Service
public class DroneMaintenanceServiceImpl implements IDroneMaintenanceService 
{
    @Autowired
    private DroneMaintenanceMapper droneMaintenanceMapper;

    /**
     * 查询无人机维保记录
     * 
     * @param id 无人机维保记录主键
     * @return 无人机维保记录
     */
    @Override
    public DroneMaintenance selectDroneMaintenanceById(String id)
    {
        return droneMaintenanceMapper.selectDroneMaintenanceById(id);
    }

    /**
     * 查询无人机维保记录列表
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 无人机维保记录
     */
    @Override
    public List<DroneMaintenance> selectDroneMaintenanceList(DroneMaintenance droneMaintenance)
    {
        return droneMaintenanceMapper.selectDroneMaintenanceList(droneMaintenance);
    }

    /**
     * 新增无人机维保记录
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 结果
     */
    @Override
    public int insertDroneMaintenance(DroneMaintenance droneMaintenance)
    {
        return droneMaintenanceMapper.insertDroneMaintenance(droneMaintenance);
    }

    /**
     * 修改无人机维保记录
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 结果
     */
    @Override
    public int updateDroneMaintenance(DroneMaintenance droneMaintenance)
    {
        return droneMaintenanceMapper.updateDroneMaintenance(droneMaintenance);
    }

    /**
     * 批量删除无人机维保记录
     * 
     * @param ids 需要删除的无人机维保记录主键
     * @return 结果
     */
    @Override
    public int deleteDroneMaintenanceByIds(String[] ids)
    {
        return droneMaintenanceMapper.deleteDroneMaintenanceByIds(ids);
    }

    /**
     * 删除无人机维保记录信息
     * 
     * @param id 无人机维保记录主键
     * @return 结果
     */
    @Override
    public int deleteDroneMaintenanceById(String id)
    {
        return droneMaintenanceMapper.deleteDroneMaintenanceById(id);
    }
}
