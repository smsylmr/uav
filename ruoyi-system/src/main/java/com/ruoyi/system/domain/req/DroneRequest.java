package com.ruoyi.system.domain.req;

import com.ruoyi.common.core.controller.BasePageRequest;
import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2020/12/22
 */
@Data
public class DroneRequest extends BasePageRequest {


    /**
     * 型号
     */
    private String model;
    /**
     * 序列号
     */
    private String serialNumber;
    /**
     * 入网状态（0已入网 1未入网）
     */
    private String networkStatus;
    /**
     * 在线状态（0在线 1离线）
     */
    private String onlineStatus;
    /**
     * 设备状态（0状态好 1状态良 2状态差 3维保中 4已报废）
     */
    private String deviceStatus;
}
