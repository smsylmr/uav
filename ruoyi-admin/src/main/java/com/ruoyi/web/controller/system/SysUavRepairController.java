package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUav;
import com.ruoyi.system.domain.SysUavRepair;
import com.ruoyi.system.service.ISysUavRepairService;
import com.ruoyi.system.service.ISysUavService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 无人机维保
 *
 * @author lsl
 */
@RestController
@RequestMapping("/system/uav/repair")
public class SysUavRepairController  extends BaseController {
    //无人机维保
    @Autowired
    private ISysUavRepairService service;

    @Autowired
    private ISysUavService sysUavService;
    

    /**
     * 获取无人机维保列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUavRepair sysUavRepair)
    {
        startPage();
        List<SysUavRepair> list = service.selectUavRepairList(sysUavRepair);
        return getDataTable(list);
    }
    /**
     * 新增无人机维保
     */
    @Log(title = "无人机维保", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult avatar(@Validated @RequestBody SysUavRepair sysUavRepair) throws Exception
    {

        sysUavRepair.setUserId(getUserId());
        sysUavRepair.setRepairNo(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        sysUavService.updateUavDeviceStatus(sysUavRepair.getUavId());
        return toAjax(service.insertUavRepair(sysUavRepair));
    }

    /**
     * 删除无人机维保
     * @param uavRepairId
     * @return
     */
    @Log(title = "无人机维保", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uavRepairId}")
    public AjaxResult remove(@PathVariable Long uavRepairId)
    {
        return toAjax(service.deleteUavRepairById(uavRepairId));
    }

    /**
     * 根据无人机编号获取无人机详细类型
     * @param uavId
     * @return
     */
    @GetMapping("/{uavRepairId}" )
    public AjaxResult getInfo(@PathVariable(value = "uavRepairId", required = false) Long uavRepairId)
    {
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(uavRepairId))
        {
            SysUavRepair sysUavRepair = service.selectUavRepairById(uavRepairId);
            ajax.put(AjaxResult.DATA_TAG, sysUavRepair);
        }
        return ajax;
    }

    /**
     * 修改无人机维保
     */
    @Log(title = "无人机维保", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult updateTYpe(@Validated @RequestBody SysUavRepair sysUavRepair)
    {
        return toAjax(service.updateUavRepair(sysUavRepair));
    }
}
