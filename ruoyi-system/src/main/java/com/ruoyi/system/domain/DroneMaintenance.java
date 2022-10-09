package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.config.ConvertBlobTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * 无人机维保记录表(DroneMaintenanceRecords)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:39:46
 */
@Data
@TableName(value = "drone_maintenance",autoResultMap = true)
public class DroneMaintenance {
    /**
    * id
    */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
    * 无人机id
    */
    private String droneId;
    /**
    * 维保时间
    */
    private LocalDateTime maintenanceTime;
    /**
    * 维保原因
    */
    @TableField(typeHandler = ConvertBlobTypeHandler.class, jdbcType = JdbcType.BLOB)
    private String maintenanceReason;


}