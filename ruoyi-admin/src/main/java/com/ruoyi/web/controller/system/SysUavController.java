package com.ruoyi.web.controller.system;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.domain.SysUav;
import com.ruoyi.system.domain.SysUavType;
import com.ruoyi.system.service.ISysUavService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.SysUavServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 无人机管理
 *
 * @author lsl
 */
@RestController
@RequestMapping("/system/uav")
public class SysUavController extends BaseController {
    //无人机增删改查
    @Autowired
    private ISysUavService service;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取用户列表
     */
    @GetMapping("/users")
    public TableDataInfo listTs(SysUser sysUser)
    {
        List<SysUser> list = sysUserService.selectUserList(sysUser);
        return getDataTable(list);
    }

    /**
     * 获取无人机列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUav sysUav)
    {
        startPage();
        List<SysUav> list = service.selectUavList(sysUav);
        return getDataTable(list);
    }
    /**
     * 新增无人机类型
     */
    @Log(title = "无人机", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult avatar(@Validated @RequestBody SysUav sysUav) throws Exception
    {
        return toAjax(service.insertUav(sysUav));
    }
    /*   *//**
     * 新增无人机类型
     *//*
    @Log(title = "无人机类型", businessType = BusinessType.UPDATE)
    @PutMapping("/type")
    public AjaxResult update(@RequestParam("img") MultipartFile file, @RequestParam("name") String name, @RequestParam("remark") String remark) throws Exception
    {
        System.out.println(file);
        System.out.println(file);
        System.out.println(name);
        if (!file.isEmpty())
        {

        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }*/

    /**
     * 删除无人机
     * @param uavId
     * @return
     */
    @Log(title = "无人机", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uavId}")
    public AjaxResult remove(@PathVariable String uavId)
    {
        System.out.println(Long.parseLong(uavId));
        return toAjax(service.deleteUavById(Long.parseLong(uavId)));
    }

    /**
     * 根据无人机编号获取无人机详细类型
     * @param uavId
     * @return
     */
    @GetMapping("/{uavId}" )
    public AjaxResult getInfo(@PathVariable(value = "uavId", required = false) Long uavId)
    {
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(uavId))
        {
            SysUav sysUav = service.selectUavById(uavId);
            ajax.put(AjaxResult.DATA_TAG, sysUav);
        }
        return ajax;
    }

    /**
     * 修改无人机
     */
    @Log(title = "无人机管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult updateTYpe(@Validated @RequestBody SysUav sysUav)
    {
        return toAjax(service.updateUav(sysUav));
    }
}
