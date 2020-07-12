package com.example.market.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class BaseModel implements Serializable {


    private static final long serialVersionUID = 6703065358452628368L;

    /**
     * 记录标识/主键（数据库自动生成）
     */
    protected Long id;

    /**
     * 是否被逻辑删除（默认是未删除）
     */
    protected Boolean isDeleted = Boolean.FALSE;

    /**
     * 创建时间（单位：秒）
     */
    protected Integer created;

    /**
     * 最后一次更新时间（单位：秒）
     */
    protected Integer updated;


}