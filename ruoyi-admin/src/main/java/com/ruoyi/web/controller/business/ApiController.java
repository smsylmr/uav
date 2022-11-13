package com.ruoyi.web.controller.business;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.bean.OrikaUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.Drone;
import com.ruoyi.system.domain.DroneFlight;
import com.ruoyi.system.domain.DroneFlightData;
import com.ruoyi.system.domain.Uav;
import com.ruoyi.system.domain.req.FlightData;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Api(tags = "数据传输模块")
@RestController
@RequestMapping("/data")
@Slf4j
public class ApiController
{

    @Resource
    private ApiService apiService;


    /**
     *
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件飞行数据")
    @Anonymous
    public AjaxResult uploadFile(@RequestPart("file") MultipartFile file,
                                 @RequestPart("data") String data)
    {
        log.info("文件飞行数据接收请求！参数：{}",JSON.toJSONString(data));
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String upload = FileUploadUtils.upload(filePath, file);
            String replace = upload.replace("/profile/upload", "");
            String url = filePath + replace;
            List<String> list = FileUtil.readLines(url, "utf-8");
            log.info("gps信息：{}",JSON.toJSONString(list));
            FlightData flightData = JSON.parseObject(data, FlightData.class);
            apiService.flightData(flightData,list);
            return AjaxResult.success();
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }


    @PostMapping("/realFlightData")
    @ApiOperation(value = "实时飞行数据")
    @Anonymous
    public AjaxResult realFlightData(@RequestBody FlightData flightData)
    {
        log.info("实时飞行数据接收请求！参数：{}",JSON.toJSONString(flightData));
        apiService.realFlightData(flightData);
        return AjaxResult.success();
    }
}
