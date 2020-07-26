package com.example.market.model;

import lombok.Data;

/**
 * @author degang
 * @date 2020/7/15
 */
@Data
public class FundStockAnalysisDTO {
    // private String code;
    private String stockName;
    private String stockNum;
    private String stockChange;
    private String stockValue;
    private Long stockRMB;
    private String fundName;
    private Integer rank;
    private Integer cxL3;
    private Integer cxL5;
}
