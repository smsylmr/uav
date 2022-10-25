package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户和角色关联 sys_user_drone
 * 
 * @author ruoyi
 */
@Data
@TableName(value = "sys_user_drone")
public class SysUserDrone
{
    /** 用户ID */
    private Long userId;
    
    /** 角色ID */
    private String droneId;


}
