package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUavRepair;

import java.util.List;

public interface ISysUavRepairService {
    /**
     * 根据条件分页查询无人机维保信息
     *
     * @param sysUavRepair 无人机维保
     * @return 无人机维保信息
     */
    public List<SysUavRepair> selectUavRepairList(SysUavRepair sysUavRepair);

    /**
     * 新增无人机维保
     *
     * @param sysUavRepair 无人机维保信息
     * @return 结果
     */
    public int insertUavRepair(SysUavRepair sysUavRepair);

    /**
     * 通过ID删除无人机维保信息
     *
     * @param uavRepairId 无人机维保ID
     * @return 结果
     */
    public int deleteUavRepairById(Long uavRepairId);

    /**
     * 通过无人机ID查询无人机详情
     *
     * @param uavRepairId 无人机维保ID
     * @return 无人机维保对象信息
     */
    public SysUavRepair selectUavRepairById(Long uavRepairId);

    /**
     * 修改无人机维保信息
     *
     * @param sysUavRepair 无人机维保
     * @return 结果
     */
    public int updateUavRepair(SysUavRepair sysUavRepair);
}
