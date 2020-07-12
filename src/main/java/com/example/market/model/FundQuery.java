package com.example.market.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 基金信息表-Query
 *
 * @author
 * @date 2020-07-12
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class FundQuery extends Query {
    /**
     * id
     */
    private Long id;
    /**
     * 基金代码
     */
    private String code;
    /**
     * 基金简称
     */
    private String name;
    /**
     * 5年涨幅
     */
    private BigDecimal incrY5;
    /**
     * 晨星3年评级
     */
    private Byte cxL3;
    /**
     * 晨星5年评级
     */
    private Byte cxL5;
    /**
     * 晨星评级日期
     */
    private String cxDay;
    /**
     * 前十重仓股票
     */
    private String heavyStock;
    /**
     * 前十重仓占净值比(%)
     */
    private BigDecimal stockRatio;
    /**
     * 股票统计日期
     */
    private String stockDay;
    /**
     * 查询数据的日期
     */
    private String queryDay;
}
