package com.example.market.dal.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractData {
    private Integer id;
    private Integer dataId;
    private Integer circleT;
    private Integer circleDiffV;
    private Integer A_T;
    private Integer A_V_H;
    private Integer A_V_L;
    private Integer A_V_E;
    private Integer B_T;
    private Integer B_V_H;
    private Integer B_V_L;
    private Integer B_V_E;
    private Integer C_T;
    private Integer C_V_H;
    private Integer C_V_L;
    private Integer C_V_E;
    private Integer D_T;
    private Integer D_V_H;
    private Integer D_V_L;
    private Integer D_V_E;
    private Integer E_T;
    private Integer E_V_H;
    private Integer E_V_L;
    private Integer E_V_E;
    private Integer F_T;
    private Integer F_V_H;
    private Integer F_V_L;
    private Integer F_V_E;
    private Integer G_T;
    private Integer G_V_H;
    private Integer G_V_L;
    private Integer G_V_E;
    private Integer H_T;
    private Integer H_V_H;
    private Integer H_V_L;
    private Integer H_V_E;
    private String remark;
}
