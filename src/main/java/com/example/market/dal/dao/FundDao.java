package com.example.market.dal.dao;

import com.example.market.dal.domain.FundDO;
import com.example.market.model.FundQuery;
import com.example.market.model.FundStockAnalysisDTO;

import java.util.List;

public interface FundDao extends BaseDao<FundDO, FundQuery> {
    /**
     * 批量插入
     * @param entities 插入实体,除isDeleted字段，其他字段确保相应字段都有默认值
     * @return
     */
    int batchInsert(List<FundDO> entities);

    List<FundStockAnalysisDTO> listAnalysisData(String queryDay);
}
