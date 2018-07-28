package com.example.market.model;

import com.example.market.dal.domain.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataForm {
    private String A_T;
    private Integer A_V_H;
    private Integer A_V_L;
    private Integer A_V_E;

    /**
     * B点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String B_T;

    /**
     * B点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer B_V_H;

    /**
     * B点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer B_V_L;

    /**
     * B点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer B_V_E;

    /**
     * C点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String C_T;

    /**
     * C点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer C_V_H;

    /**
     * C点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer C_V_L;

    /**
     * C点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer C_V_E;

    /**
     * D点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String D_T;

    /**
     * D点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer D_V_H;

    /**
     * D点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer D_V_L;

    /**
     * D点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer D_V_E;

    /**
     * E点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String E_T;

    /**
     * E点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer E_V_H;

    /**
     * E点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer E_V_L;

    /**
     * E点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer E_V_E;

    /**
     * F点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String F_T;

    /**
     * F点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer F_V_H;

    /**
     * F点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer F_V_L;

    /**
     * F点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer F_V_E;

    /**
     * G点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String G_T;

    /**
     * G点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer G_V_H;

    /**
     * G点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer G_V_L;

    /**
     * G点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer G_V_E;

    /**
     * H点时间，单位min
     * isNullAble:0,defaultVal:0
     */
    private String H_T;

    /**
     * H点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer H_V_H;

    /**
     * H点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer H_V_L;

    /**
     * H点价位
     * isNullAble:0,defaultVal:0
     */
    private Integer H_V_E;

    /**
     * 图片地址
     * isNullAble:0,defaultVal:
     */
    private String imageUrl;

    /**
     * 备注
     * isNullAble:0,defaultVal:
     */
    private String remark;

    private Integer date;

    public Data toData() {
        Data data = Data.builder()
                .A_V_H(A_V_H)
                .A_V_L(A_V_L)
                .A_V_E(A_V_E)
                .B_V_H(B_V_H)
                .B_V_L(B_V_L)
                .B_V_E(B_V_E)
                .C_V_H(C_V_H)
                .C_V_L(C_V_L)
                .C_V_E(C_V_E)
                .D_V_H(D_V_H)
                .D_V_L(D_V_L)
                .D_V_E(D_V_E)
                .E_V_H(E_V_H)
                .E_V_L(E_V_L)
                .E_V_E(E_V_E)
                .F_V_H(F_V_H)
                .F_V_L(F_V_L)
                .F_V_E(F_V_E)
                .G_V_H(G_V_H)
                .G_V_L(G_V_L)
                .G_V_E(G_V_E)
                .H_V_H(H_V_H)
                .H_V_L(H_V_L)
                .H_V_E(H_V_E)
                .date(date)
                .build();
        data.setA_T(minuteTransfer(date, A_T));
        data.setB_T(minuteTransfer(date, B_T));
        data.setC_T(minuteTransfer(date, C_T));
        data.setD_T(minuteTransfer(date, D_T));
        data.setE_T(minuteTransfer(date, E_T));
        data.setF_T(minuteTransfer(date, F_T));
        if (!StringUtils.isEmpty(H_T)) {
            data.setG_T(minuteTransfer(date, G_T));
            data.setH_T(minuteTransfer(date, H_T));
        }

        boolean upward = data.getB_V_E() - data.getA_V_E() > 0;
        data.setCircleT(data.getH_T() == null ? data.getF_T() - data.getA_T() : data.getH_T() - data.getA_T());
        data.setCircleDiffV(data.getH_T() == null
                ? (upward ? data.getF_V_H() - data.getA_V_L() : data.getF_V_L() - data.getA_V_H())
                : (upward ? data.getH_V_H() - data.getA_V_L() : data.getH_V_L() - data.getA_V_H())
        );

        return data;

    }

    private Integer minuteTransfer(Integer day, String shortTime) {
        LocalDate date = LocalDate.parse(day.toString(), DateTimeFormatter.BASIC_ISO_DATE);
        LocalTime time = LocalTime.parse(shortTime, DateTimeFormatter.ofPattern("HHmm"));
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return (int)(dateTime.toEpochSecond(ZoneOffset.of("+8")) / 60);
    }
}
