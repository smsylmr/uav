package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.DroneMaintenance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 无人机维保表(DroneMaintenance)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-09 16:45:17
 */
public interface DroneMaintenanceMapper extends BaseMapper<DroneMaintenance> {

    @Select("select * from drone_maintenance where drone_id = #{droneId} order by maintenance_time desc limit 1")
    DroneMaintenance getLastMaintenanceByDroneId(@Param("droneId") String droneId);

    @Select("select count(1) from drone_maintenance where drone_id = #{droneId}")
    Integer getCountByDroneId(@Param("droneId") String droneId);
}