package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.service.IDroneFlightService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无人机维保记录Controller
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@RestController
@RequestMapping("/system/flight")
public class DroneFlightController extends BaseController
{
    @Autowired
    private IDroneFlightService droneFlightService;

    /**
     * 查询无人机维保记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:flight:list')")
    @GetMapping("/list")
    public TableDataInfo list(DroneFlight droneFlight)
    {
        startPage();
        List<DroneFlight> list = droneFlightService.selectDroneFlightList(droneFlight);
        return getDataTable(list);
    }

    /**
     * 导出无人机维保记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:flight:export')")
    @Log(title = "无人机维保记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DroneFlight droneFlight)
    {
        List<DroneFlight> list = droneFlightService.selectDroneFlightList(droneFlight);
        ExcelUtil<DroneFlight> util = new ExcelUtil<DroneFlight>(DroneFlight.class);
        util.exportExcel(response, list, "无人机维保记录数据");
    }

    /**
     * 获取无人机维保记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:flight:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(droneFlightService.selectDroneFlightById(id));
    }

    /**
     * 新增无人机维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:flight:add')")
    @Log(title = "无人机维保记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DroneFlight droneFlight)
    {
        return toAjax(droneFlightService.insertDroneFlight(droneFlight));
    }

    /**
     * 修改无人机维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:flight:edit')")
    @Log(title = "无人机维保记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DroneFlight droneFlight)
    {
        return toAjax(droneFlightService.updateDroneFlight(droneFlight));
    }

    /**
     * 删除无人机维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:flight:remove')")
    @Log(title = "无人机维保记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(droneFlightService.deleteDroneFlightByIds(ids));
    }
}
