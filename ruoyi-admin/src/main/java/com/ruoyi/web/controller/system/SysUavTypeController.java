package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.domain.SysUavType;
import com.ruoyi.system.service.ISysUavTYpeService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 无人机类型管理
 *
 * @author lsl
 */
@RestController
@RequestMapping("/system/uav")
public class SysUavTypeController extends BaseController {
    @Autowired
    private ISysUavTYpeService service;
    /**
     * 获取无人机列表
     */
    @GetMapping("/type/ts")
    public TableDataInfo listTs(SysUavType sysUavType)
    {
        List<SysUavType> list = service.selectUserList(sysUavType);
        return getDataTable(list);
    }
    /**
     * 获取无人机列表
     */
    @GetMapping("/type/list")
    public TableDataInfo list(SysUavType sysUavType)
    {
        startPage();
        List<SysUavType> list = service.selectUserList(sysUavType);
        return getDataTable(list);
    }
    /**
     * 新增无人机类型
     */
    @Log(title = "无人机类型", businessType = BusinessType.INSERT)
    @PostMapping("/type")
    public AjaxResult avatar(@RequestParam("img") MultipartFile file, @RequestParam("uavTypeName") String uavTypeName, @RequestParam("uavRemark") String uavRemark) throws Exception
    {
        if (!file.isEmpty())
        {
            SysUavType sysUavType = new SysUavType();
            sysUavType.setUavTypeName(uavTypeName);
            sysUavType.setUavRemark(uavRemark);
            // 上传文件且获取路径
            String imgPath = FileUploadUtils.upload(RuoYiConfig.getUavTypePath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            sysUavType.setImgPath(imgPath);
            return toAjax(service.insertUavType(sysUavType));
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
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
     * 删除无人机类型
     * @param uavTypeId
     * @return
     */
    @Log(title = "无人机类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/type/{uavTypeId}")
    public AjaxResult remove(@PathVariable String uavTypeId)
    {
        System.out.println(Long.parseLong(uavTypeId));
        return toAjax(service.deleteUserById(Long.parseLong(uavTypeId)));
    }

    /**
     * 根据无人机编号获取无人机详细类型
     * @param uavTypeId
     * @return
     */
    @GetMapping(value = { "type/{uavTypeId}" })
    public AjaxResult getInfo(@PathVariable(value = "uavTypeId", required = false) Long uavTypeId)
    {
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(uavTypeId))
        {
            SysUavType sysUavType = service.selectUavTypeById(uavTypeId);
            ajax.put(AjaxResult.DATA_TAG, sysUavType);
        }
        return ajax;
    }

    /**
     * 修改无人机类型
     */
    @Log(title = "无人机类型管理", businessType = BusinessType.UPDATE)
    @PutMapping("/type")
    public AjaxResult updateTYpe(@Validated @RequestBody SysUavType sysUavType)
    {
        return toAjax(service.updateUavType(sysUavType));
    }
}
