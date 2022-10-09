package com.ruoyi.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/22
 */
@Data
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页记录数
     */
    private long size;
    /**
     * 总页数
     */
    private long count;
    /**
     * 当前页数
     */
    private long page;
    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.total = totalCount;
        this.size = pageSize;
        this.page = currPage;
        this.count = (long) Math.ceil(totalCount/pageSize);
    }

    /**
     * 分页
     * @param result
     * @param page
     */
    public PageUtil(List<T> result , IPage page) {
        this.list = result;
        this.total = page.getTotal();
        this.size = page.getSize();
        this.page = page.getCurrent();
        this.count = page.getPages();
    }

    /**
     * 分页
     */
    public PageUtil(IPage<T> page) {
        this.list = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.page = page.getCurrent();
        this.count = page.getPages();
    }


}
