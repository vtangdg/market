package com.example.market.dal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 基金持仓股票信息表-DO
 *
 * @author degang
 * @date 2020-07-13
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class FundStockDO extends BaseModel {
    /**
     * 基金代码
     */
    private String code;
    /**
     * 基金简称
     */
    private String name;
    /**
     * 在所处基金中排名
     */
    private Byte rank;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 持股市值
     */
    private String stockValue;
    /**
     * 持股数量
     */
    private String stockNum;
    /**
     * 持股变动
     */
    private String stockChange;
    /**
     * 占净值比（%）
     */
    private BigDecimal valueRate;
    /**
     * 占总值比（%）
     */
    private BigDecimal totalValueRate;
    /**
     * 占股票投资比（%）
     */
    private BigDecimal stockValueRate;
    /**
     * 关联fund表id
     */
    private Long fundId;
}