package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysUavType;

import java.util.List;

public interface ISysUavTYpeService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUavType 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUavType> selectUserList(SysUavType sysUavType);

    /**
     * 新增无人机类型
     *
     * @param sysUavType 无人机类型信息
     * @return 结果
     */
    public int insertUavType(SysUavType sysUavType);


    /**
     * 通过无人机类型ID删除无人机类型
     *
     * @param uavTypeId 无人机类型
     * @return 结果
     */
    public int deleteUserById(Long uavTypeId);

    /**
     * 通过无人机类型ID查询无人机类型详情
     *
     * @param uavTypeId 无人机类型ID
     * @return 无人机类型对象信息
     */
    public SysUavType selectUavTypeById(Long uavTypeId);

    /**
     * 修改无人机类型信息
     *
     * @param sysUavType 无人机类型
     * @return 结果
     */
    public int updateUavType(SysUavType sysUavType);
}
