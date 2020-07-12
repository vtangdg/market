package com.example.market.dal.dao;

import com.example.market.dal.domain.FundDO;
import com.example.market.model.FundQuery;

import java.util.List;

public interface FundMapper extends BaseDao<FundDO, FundQuery> {
    /**
     * 批量插入
     * @param entities 插入实体,除isDeleted字段，其他字段确保相应字段都有默认值
     * @return
     */
    int batchInsert(List<FundDO> entities);
}
