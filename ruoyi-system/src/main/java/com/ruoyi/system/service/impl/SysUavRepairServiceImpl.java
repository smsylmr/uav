package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysUavRepair;
import com.ruoyi.system.mapper.SysUavMapper;
import com.ruoyi.system.mapper.SysUavRepairMapper;
import com.ruoyi.system.service.ISysUavRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUavRepairServiceImpl implements ISysUavRepairService {
    @Autowired
    private SysUavRepairMapper mapper;
    @Override
    public List<SysUavRepair> selectUavRepairList(SysUavRepair sysUavRepair) {
        return mapper.selectUavRepairList(sysUavRepair);
    }

    @Override
    public int insertUavRepair(SysUavRepair sysUavRepair) {
        return mapper.insertUavRepair(sysUavRepair);
    }

    @Override
    public int deleteUavRepairById(Long uavRepairId) {
        return mapper.deleteUavRepairById(uavRepairId);
    }

    @Override
    public SysUavRepair selectUavRepairById(Long uavRepairId) {
        return mapper.selectUavRepairById(uavRepairId);
    }

    @Override
    public int updateUavRepair(SysUavRepair sysUavRepair) {
        return mapper.updateUavRepair(sysUavRepair);
    }
}
