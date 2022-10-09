package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.vo.DroneData;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 无人机飞行表(DroneFlight)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-09 16:45:17
 */
public interface DroneFlightMapper extends BaseMapper<DroneFlight> {

    DroneData getFlightData(@Param("droneId") String droneId);


    DroneData getFlightDataAfterLastMaintenance(@Param("droneId") String droneId, @Param("flightTime") LocalDateTime flightTime);

    DroneFlight getLastFlightByDroneId(@Param("droneId") String droneId);
}