package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无人机基本信息对象 drone
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public class Drone extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String modelPhoto;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;

    /** 保险单号 */
    @Excel(name = "保险单号")
    private String policyNo;

    /** 入网状态（0已入网 1未入网） */
    @Excel(name = "入网状态", readConverterExp = "0=已入网,1=未入网")
    private String networkStatus;

    /** 在线状态（0在线 1离线） */
    @Excel(name = "在线状态", readConverterExp = "0=在线,1=离线")
    private String onlineStatus;

    /** 设备状态（0状态好 1状态良 2状态差 3维保中 4已报废） */
    @Excel(name = "设备状态", readConverterExp = "0=状态好,1=状态良,2=状态差,3=维保中,4=已报废")
    private String deviceStatus;

    /** 是否删除（0否 1是） */
    private String delFlag;

    /** 购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchasingDate;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setModelPhoto(String modelPhoto) 
    {
        this.modelPhoto = modelPhoto;
    }

    public String getModelPhoto() 
    {
        return modelPhoto;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setNetworkStatus(String networkStatus) 
    {
        this.networkStatus = networkStatus;
    }

    public String getNetworkStatus() 
    {
        return networkStatus;
    }
    public void setOnlineStatus(String onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineStatus() 
    {
        return onlineStatus;
    }
    public void setDeviceStatus(String deviceStatus) 
    {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceStatus() 
    {
        return deviceStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setPurchasingDate(Date purchasingDate) 
    {
        this.purchasingDate = purchasingDate;
    }

    public Date getPurchasingDate() 
    {
        return purchasingDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("model", getModel())
            .append("modelPhoto", getModelPhoto())
            .append("serialNumber", getSerialNumber())
            .append("policyNo", getPolicyNo())
            .append("networkStatus", getNetworkStatus())
            .append("onlineStatus", getOnlineStatus())
            .append("deviceStatus", getDeviceStatus())
            .append("delFlag", getDelFlag())
            .append("purchasingDate", getPurchasingDate())
            .toString();
    }
}
