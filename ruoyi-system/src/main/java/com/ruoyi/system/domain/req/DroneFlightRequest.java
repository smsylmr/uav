package com.ruoyi.system.domain.req;

import com.ruoyi.common.core.controller.BasePageRequest;
import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/9/3
 */
@Data
public class DroneFlightRequest extends BasePageRequest {
    /**
     * 无人机id
     */
    private String droneId;
}
