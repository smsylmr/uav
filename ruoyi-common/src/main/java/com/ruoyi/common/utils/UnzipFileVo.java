package com.ruoyi.common.utils;

import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/10/23
 */
@Data
public class UnzipFileVo {
    /**
     * 类型：0-文件夹；1-文件
     */
    private Integer type;

    /**
     * 文件路径（如：src/main/java/com/zyq/entity/User.java）
     */
    private String path;

    /**
     * 文件内容
     */
    private String content;

}
