package com.example.market.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Data
@ToString
public class Query extends BaseQuery {

    private static final Integer MAX_PAGE_SIZE = 50000;

    private static final Integer DEFAULT_PAGE_SIZE = 50;

    /**
     * 排序
     */
    private static final List<String> OrderSorts = Arrays.asList("asc", "desc", "ASC", "DESC");

    /**
     * 每页大小
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前页码，从1开始
     */
    private Integer pageNumber = 1;

    /**
     * 是否需要统计数据，统计结果在totalItem中
     */
    private boolean needCount;

    /**
     * OrderBy子句
     */
    private String orderBy;

    /**
     * 排序字段(bootstrapTable插件,特有查询参数)
     */
    private String sortName;

    /**
     * 升序或者降序(bootstrapTable插件,特有查询参数)
     */
    private String sortOrder;

    public void setPageNumber(Integer pageNumber) {
        if (null == pageNumber || pageNumber <= 0) {
            this.pageNumber = 1;
        } else if(pageSize*pageNumber >= 50000 ) {
            throw new IllegalStateException("查询数据超过5W");
        }else {
            this.pageNumber = pageNumber;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (null == pageSize || pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else if (pageSize > MAX_PAGE_SIZE) {
            this.pageSize = MAX_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    @Override
    public String getOrderBy() {
        if (!StringUtils.isEmpty(this.orderBy)) {
            return orderBy;
        }
        if (!StringUtils.isEmpty(this.sortName)
                && !StringUtils.isEmpty(this.sortOrder)
                && OrderSorts.contains(this.sortOrder.toLowerCase())) {
            return String.format("%s %s", this.sortName, this.sortOrder);
        }
        return this.orderBy;
    }

    /**
     * limit子句第一个参数
     */
    public Integer getStart() {
        return (this.pageNumber - 1) * pageSize;
    }

}