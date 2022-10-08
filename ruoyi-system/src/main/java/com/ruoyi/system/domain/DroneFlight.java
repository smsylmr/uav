package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无人机维保记录对象 drone_flight
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public class DroneFlight extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 无人机ID */
    @Excel(name = "无人机ID")
    private String droneId;

    /** 本次飞行里程 */
    @Excel(name = "本次飞行里程")
    private BigDecimal flightMileage;

    /** 本次飞行耗时 */
    @Excel(name = "本次飞行耗时")
    private BigDecimal flightConsume;

    /** 飞行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "飞行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date flightTime;

    /** 最后在线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后在线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOnlineTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDroneId(String droneId) 
    {
        this.droneId = droneId;
    }

    public String getDroneId() 
    {
        return droneId;
    }
    public void setFlightMileage(BigDecimal flightMileage) 
    {
        this.flightMileage = flightMileage;
    }

    public BigDecimal getFlightMileage() 
    {
        return flightMileage;
    }
    public void setFlightConsume(BigDecimal flightConsume) 
    {
        this.flightConsume = flightConsume;
    }

    public BigDecimal getFlightConsume() 
    {
        return flightConsume;
    }
    public void setFlightTime(Date flightTime) 
    {
        this.flightTime = flightTime;
    }

    public Date getFlightTime() 
    {
        return flightTime;
    }
    public void setLastOnlineTime(Date lastOnlineTime) 
    {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Date getLastOnlineTime() 
    {
        return lastOnlineTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("droneId", getDroneId())
            .append("flightMileage", getFlightMileage())
            .append("flightConsume", getFlightConsume())
            .append("flightTime", getFlightTime())
            .append("lastOnlineTime", getLastOnlineTime())
            .toString();
    }
}
