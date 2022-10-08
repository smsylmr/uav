package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DroneFlight;

/**
 * 无人机维保记录Service接口
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public interface IDroneFlightService 
{
    /**
     * 查询无人机维保记录
     * 
     * @param id 无人机维保记录主键
     * @return 无人机维保记录
     */
    public DroneFlight selectDroneFlightById(String id);

    /**
     * 查询无人机维保记录列表
     * 
     * @param droneFlight 无人机维保记录
     * @return 无人机维保记录集合
     */
    public List<DroneFlight> selectDroneFlightList(DroneFlight droneFlight);

    /**
     * 新增无人机维保记录
     * 
     * @param droneFlight 无人机维保记录
     * @return 结果
     */
    public int insertDroneFlight(DroneFlight droneFlight);

    /**
     * 修改无人机维保记录
     * 
     * @param droneFlight 无人机维保记录
     * @return 结果
     */
    public int updateDroneFlight(DroneFlight droneFlight);

    /**
     * 批量删除无人机维保记录
     * 
     * @param ids 需要删除的无人机维保记录主键集合
     * @return 结果
     */
    public int deleteDroneFlightByIds(String[] ids);

    /**
     * 删除无人机维保记录信息
     * 
     * @param id 无人机维保记录主键
     * @return 结果
     */
    public int deleteDroneFlightById(String id);
}
