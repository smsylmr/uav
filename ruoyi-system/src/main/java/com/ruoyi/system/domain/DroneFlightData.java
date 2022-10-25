package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 无人机飞行数据信息表(DroneFlightData)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:39:46
 */
@Data
@TableName(value = "drone_flight_data")
public class DroneFlightData {
    /**
    * id
    */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 飞行序号
     */
    private String flyNum;
    /**
    * 无人机id
    */
    private String droneId;
    /**
     * 本次飞行里程
     */
    private String gps;


}