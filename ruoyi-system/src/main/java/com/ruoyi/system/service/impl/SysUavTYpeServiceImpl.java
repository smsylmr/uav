package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysUavType;
import com.ruoyi.system.mapper.SysUavTypeMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUavTYpeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUavTYpeServiceImpl implements ISysUavTYpeService {
    @Autowired
    private SysUavTypeMapper sysUavTypeMapper;
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Override
    public List<SysUavType> selectUserList(SysUavType sysUavType) {
        return sysUavTypeMapper.selectUavTypeList(sysUavType);
    }

    @Override
    public int insertUavType(SysUavType sysUavType) {
        return sysUavTypeMapper.insertUavType(sysUavType);
    }

    @Override
    public int deleteUserById(Long uavTypeId) {
        System.out.println(uavTypeId);
        return sysUavTypeMapper.deleteUavTypeById(uavTypeId);
    }

    @Override
    public SysUavType selectUavTypeById(Long uavTypeId) {
        return sysUavTypeMapper.selectUavTypeById(uavTypeId);
    }

    @Override
    public int updateUavType(SysUavType sysUavType) {
        return sysUavTypeMapper.updateUavType(sysUavType);
    }
}
