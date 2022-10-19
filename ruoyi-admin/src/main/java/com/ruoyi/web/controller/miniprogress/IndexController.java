package com.ruoyi.web.controller.miniprogress;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.MiniLoginBody;
import com.ruoyi.common.utils.PageUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.MiniLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.domain.req.DroneRequest;
import com.ruoyi.system.domain.vo.DroneVO;
import com.ruoyi.system.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Api(tags = "小程序登录模块")
@RestController
@RequestMapping("/miniProgress")
public class IndexController
{
    @Resource
    private DroneService droneService;

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    @Anonymous
    public AjaxResult list(@RequestBody DroneRequest droneRequest){
        PageUtil<DroneVO> list = droneService.list4Mini(droneRequest);
        return  AjaxResult.success(list);
    }


}
