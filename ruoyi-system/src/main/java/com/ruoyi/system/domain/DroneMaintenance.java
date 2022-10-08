package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无人机维保记录对象 drone_maintenance
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public class DroneMaintenance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 无人机ID */
    @Excel(name = "无人机ID")
    private String droneId;

    /** 维保时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维保时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintenanceTime;

    /** 维保原因 */
    @Excel(name = "维保原因")
    private String maintenanceReason;

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
    public void setMaintenanceTime(Date maintenanceTime) 
    {
        this.maintenanceTime = maintenanceTime;
    }

    public Date getMaintenanceTime() 
    {
        return maintenanceTime;
    }
    public void setMaintenanceReason(String maintenanceReason) 
    {
        this.maintenanceReason = maintenanceReason;
    }

    public String getMaintenanceReason() 
    {
        return maintenanceReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("droneId", getDroneId())
            .append("maintenanceTime", getMaintenanceTime())
            .append("maintenanceReason", getMaintenanceReason())
            .toString();
    }
}
