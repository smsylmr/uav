package com.ruoyi.system.domain.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linmr
 * @description:
 * @date 2022/10/20
 */
@Data
public class FlightData {
    //型号
    private String model;
    //序列号
    private String SN;
    //保单号
    private String policy;
    //购买时间
    private LocalDateTime time;
    //当时定位数据
    private String gps;
    //飞行序号
    private String flyNum;
    //开始时间
    private LocalDateTime startTime;
    //本次飞行持续时间单位秒
    private String flyTime;
    //飞行结束标记
    private String isEnd;
}
