package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysUavType;

import java.util.List;

/**
 * 无人机类型表
 */
public interface SysUavTypeMapper {
    /**
     * 根据条件分页查询无人机类型
     *
     * @param sysUavType 无人机类型
     * @return 无人机类型信息
     */
    public List<SysUavType> selectUavTypeList(SysUavType sysUavType);

    /**
     * 新增无人机类型
     *
     * @param sysUavType 无人机类型信息
     * @return 结果
     */
    public int insertUavType(SysUavType sysUavType);

    /**
     * 通过用户ID删除无人机类型
     *
     * @param uavTypeId 无人机类型ID
     * @return 结果
     */
    public int deleteUavTypeById(Long uavTypeId);

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
