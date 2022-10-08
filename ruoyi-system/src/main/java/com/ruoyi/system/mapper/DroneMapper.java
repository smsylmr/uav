package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Drone;

/**
 * 无人机基本信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public interface DroneMapper 
{
    /**
     * 查询无人机基本信息
     * 
     * @param id 无人机基本信息主键
     * @return 无人机基本信息
     */
    public Drone selectDroneById(String id);

    /**
     * 查询无人机基本信息列表
     * 
     * @param drone 无人机基本信息
     * @return 无人机基本信息集合
     */
    public List<Drone> selectDroneList(Drone drone);

    /**
     * 新增无人机基本信息
     * 
     * @param drone 无人机基本信息
     * @return 结果
     */
    public int insertDrone(Drone drone);

    /**
     * 修改无人机基本信息
     * 
     * @param drone 无人机基本信息
     * @return 结果
     */
    public int updateDrone(Drone drone);

    /**
     * 删除无人机基本信息
     * 
     * @param id 无人机基本信息主键
     * @return 结果
     */
    public int deleteDroneById(String id);

    /**
     * 批量删除无人机基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDroneByIds(String[] ids);
}
