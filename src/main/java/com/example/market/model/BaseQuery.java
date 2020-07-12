package com.example.market.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class BaseQuery implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * ids
     */
    private Collection<Long> ids;
    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 创建人工号
     */
    protected Long creator;
}
