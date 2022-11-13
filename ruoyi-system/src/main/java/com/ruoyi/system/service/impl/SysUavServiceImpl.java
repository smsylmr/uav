package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysUav;
import com.ruoyi.system.domain.SysUavType;
import com.ruoyi.system.mapper.SysUavMapper;
import com.ruoyi.system.service.ISysUavService;
import com.ruoyi.system.service.ISysUavTYpeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUavServiceImpl implements ISysUavService {

    @Autowired
    private SysUavMapper mapper;

    @Override
    public List<SysUav> selectUavList(SysUav sysUav) {
        return mapper.selectUavList(sysUav);
    }

    @Override
    public int insertUav(SysUav sysUav) {
        return mapper.insertUav(sysUav);
    }

    @Override
    public int deleteUavById(Long uavId) {
        return mapper.deleteUavById(uavId);
    }

    @Override
    public SysUav selectUavById(Long uavId) {
        return mapper.selectUavById(uavId);
    }

    @Override
    public int updateUav(SysUav sysUav) {
        return mapper.updateUav(sysUav);
    }

    @Override
    public int updateUavDeviceStatus(Long uavId) {
        return mapper.updateUavDeviceStatus(uavId);
    }
}
