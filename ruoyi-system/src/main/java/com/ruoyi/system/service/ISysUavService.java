package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUav;

import java.util.List;

public interface ISysUavService {
    /**
     * 根据条件分页查询无人机
     *
     * @param sysUav 无人机类型
     * @return 无人机类型信息
     */
    public List<SysUav> selectUavList(SysUav sysUav);

    /**
     * 新增无人机
     *
     * @param sysUav 无人机信息
     * @return 结果
     */
    public int insertUav(SysUav sysUav);

    /**
     * 通过ID删除无人机
     *
     * @param uavId 无人机ID
     * @return 结果
     */
    public int deleteUavById(Long uavId);

    /**
     * 通过无人机ID查询无人机详情
     *
     * @param uavId 无人机ID
     * @return 无人机类型对象信息
     */
    public SysUav selectUavById(Long uavId);

    /**
     * 修改无人机信息
     *
     * @param sysUav 无人机
     * @return 结果
     */
    public int updateUav(SysUav sysUav);

    /**
     * 修改无人机状态信息
     * @param uavId
     * @return
     */
    int updateUavDeviceStatus(Long uavId);
}
