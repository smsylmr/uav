package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.service.IDroneService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无人机基本信息Controller
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Api("无人机基本信息管理")
@RestController
@RequestMapping("/system/drone")
public class DroneController extends BaseController
{
    @Autowired
    private IDroneService droneService;

    /**
     * 查询无人机基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:drone:list')")
    @GetMapping("/list")
    @ApiOperation("查询无人机基本信息列表")
    public TableDataInfo list(Drone drone)
    {
        startPage();
        List<Drone> list = droneService.selectDroneList(drone);
        return getDataTable(list);
    }

    /**
     * 导出无人机基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:drone:export')")
    @Log(title = "无人机基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Drone drone)
    {
        List<Drone> list = droneService.selectDroneList(drone);
        ExcelUtil<Drone> util = new ExcelUtil<Drone>(Drone.class);
        util.exportExcel(response, list, "无人机基本信息数据");
    }

    /**
     * 获取无人机基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:drone:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取无人机基本信息详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(droneService.selectDroneById(id));
    }

    /**
     * 新增无人机基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:drone:add')")
    @Log(title = "无人机基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增无人机基本信息")
    public AjaxResult add(@RequestBody Drone drone)
    {
        return toAjax(droneService.insertDrone(drone));
    }

    /**
     * 修改无人机基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:drone:edit')")
    @Log(title = "无人机基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改无人机基本信息")
    public AjaxResult edit(@RequestBody Drone drone)
    {
        return toAjax(droneService.updateDrone(drone));
    }

    /**
     * 删除无人机基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:drone:remove')")
    @Log(title = "无人机基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除无人机基本信息")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(droneService.deleteDroneByIds(ids));
    }
}
