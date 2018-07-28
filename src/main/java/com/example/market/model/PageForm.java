package com.example.market.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageForm<T> implements Serializable{
    private static final long serialVersionUID = -353081810855677157L;
    /**
     * 当前页码
     */
    int pageNum;
    /**
     * 每页条数
     */
    int pageSize;
    /**
     * 总数
     */
    int total;
    /**
     * 结果集
     */
    List<T> list;

    boolean end;

    public PageForm() {
    }

    public PageForm(int pageNum, int pageSize, int total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.end = total <= pageNum * pageSize;
    }

    public PageForm(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = 0;
        this.list = new ArrayList<>();
        this.end = true;
    }
}
