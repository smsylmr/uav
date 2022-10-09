package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.vo.DroneVO;

/**
 * 无人机表(Drone)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface DroneService extends IService<Drone> {

    PageUtil<DroneVO> list(DroneRequest droneRequest);

    void del(String droneId);
}