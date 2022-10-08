package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DroneMaintenance;

/**
 * 无人机维保记录Service接口
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public interface IDroneMaintenanceService 
{
    /**
     * 查询无人机维保记录
     * 
     * @param id 无人机维保记录主键
     * @return 无人机维保记录
     */
    public DroneMaintenance selectDroneMaintenanceById(String id);

    /**
     * 查询无人机维保记录列表
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 无人机维保记录集合
     */
    public List<DroneMaintenance> selectDroneMaintenanceList(DroneMaintenance droneMaintenance);

    /**
     * 新增无人机维保记录
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 结果
     */
    public int insertDroneMaintenance(DroneMaintenance droneMaintenance);

    /**
     * 修改无人机维保记录
     * 
     * @param droneMaintenance 无人机维保记录
     * @return 结果
     */
    public int updateDroneMaintenance(DroneMaintenance droneMaintenance);

    /**
     * 批量删除无人机维保记录
     * 
     * @param ids 需要删除的无人机维保记录主键集合
     * @return 结果
     */
    public int deleteDroneMaintenanceByIds(String[] ids);

    /**
     * 删除无人机维保记录信息
     * 
     * @param id 无人机维保记录主键
     * @return 结果
     */
    public int deleteDroneMaintenanceById(String id);
}
