package com.ruoyi.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.vo.DroneVO;

/**
 * 无人机表(Drone)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-09 16:45:17
 */
public interface DroneMapper extends BaseMapper<Drone> {

    IPage<Drone> listByUserId(IPage<Drone> page, Long userId);

}