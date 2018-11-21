package com.example.market.dal.domain;
import com.example.market.model.DataDisplayDTO;
import com.example.market.model.DataDisplayProcessVO;
import com.example.market.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.DecimalFormat;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data implements Serializable {

    private static final long serialVersionUID = 1531908826891L;


    /**
    * 主键
    * 
    * isNullAble:0(double)
    */
    private Integer id;

    /**
    * 周期耗时，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer circleT;

    /**
    * 周期差值
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer circleDiffV;

    /**
    * A点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer A_T;

    /**
    * A点价位-高
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer A_V_H;

    /**
    * A点价位-低
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer A_V_L;

    /**
    * A点价位-收
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer A_V_E;

    /**
    * B点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer B_T;

    /**
    * B点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer B_V_H;

    /**
    * B点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer B_V_L;

    /**
    * B点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer B_V_E;

    /**
    * C点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer C_T;

    /**
    * C点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer C_V_H;

    /**
    * C点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer C_V_L;

    /**
    * C点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer C_V_E;

    /**
    * D点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer D_T;

    /**
    * D点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer D_V_H;

    /**
    * D点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer D_V_L;

    /**
    * D点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer D_V_E;

    /**
    * E点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer E_T;

    /**
    * E点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer E_V_H;

    /**
    * E点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer E_V_L;

    /**
    * E点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer E_V_E;

    /**
    * F点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer F_T;

    /**
    * F点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer F_V_H;

    /**
    * F点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer F_V_L;

    /**
    * F点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer F_V_E;

    /**
    * G点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer G_T;

    /**
    * G点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer G_V_H;

    /**
    * G点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer G_V_L;

    /**
    * G点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer G_V_E;

    /**
    * H点时间，单位min
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer H_T;

    /**
    * H点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer H_V_H;

    /**
    * H点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer H_V_L;

    /**
    * H点价位
    * isNullAble:0(double),defaultVal:0(double)
    */
    private Integer H_V_E;

    /**
    * 图片地址
    * isNullAble:0(double),defaultVal:
(double)    */
    private String imageUrl;

    /**
    * 备注
    * isNullAble:0(double),defaultVal:
(double)    */
    private String remark;

    private Integer date;

    private Boolean special;

    public DataDisplayDTO toDataDisplayDTO() {
        boolean upward = this.circleDiffV > 0;
        Integer startV = upward ? A_V_L : A_V_H;

        DataDisplayDTO displayDTO = DataDisplayDTO.builder()
                .circleT(this.circleT)
                .circleDiffV(this.circleDiffV)
                .date(this.date)
                .startV(startV)
                .imageUrl(imageUrl)
                .remark(remark)
                .build();
        displayDTO.setEndV(displayDTO.getStartV() + circleDiffV);
        displayDTO.setCircleDiffVP(getPercent((double)circleDiffV, startV));

        // A、C、E、G同峰谷，B、D、F、H同峰谷
        displayDTO.setA2B(new DataDisplayProcessVO(
                B_T - A_T,
                upward ? B_V_H - A_V_L : B_V_L - A_V_H,
                upward ? getPercent(A_V_L, B_V_H) : getPercent(A_V_H, B_V_L),
                upward ? getPercent(A_V_L, B_V_H, startV) : getPercent(A_V_H, B_V_L, startV)
        ));
        displayDTO.setB2C(new DataDisplayProcessVO(
                C_T - B_T,
                upward ? C_V_L - B_V_H : C_V_H - B_V_L,
                upward ? getPercent(B_V_H, C_V_L) : getPercent(B_V_L, C_V_H),
                upward ? getPercent(B_V_H, C_V_L, startV) : getPercent(B_V_L, C_V_H, startV)
        ));
        displayDTO.setC2D(new DataDisplayProcessVO(
                D_T - C_T,
                upward ? D_V_H - C_V_L : D_V_L - C_V_H,
                upward ? getPercent(C_V_L, D_V_H) : getPercent(C_V_H, D_V_L),
                upward ? getPercent(C_V_L, D_V_H, startV) : getPercent(C_V_H, D_V_L, startV)
        ));
        displayDTO.setD2E(new DataDisplayProcessVO(
                E_T - D_T,
                upward ? E_V_L - D_V_H : E_V_H - D_V_L,
                upward ? getPercent(D_V_H, E_V_L) : getPercent(D_V_L, E_V_H),
                upward ? getPercent(D_V_H, E_V_L, startV) : getPercent(D_V_L, E_V_H, startV)
        ));
        displayDTO.setE2F(new DataDisplayProcessVO(
                F_T - E_T,
                upward ? F_V_H - E_V_L : F_V_L - E_V_H,
                upward ? getPercent(E_V_L, F_V_H) : getPercent(E_V_H, F_V_L),
                upward ? getPercent(E_V_L, F_V_H, startV) : getPercent(E_V_H, F_V_L, startV)
        ));
        // 延长浪
        if (!NumberUtil.isEmpty(H_T)) {
            displayDTO.setF2G(new DataDisplayProcessVO(
                    G_T - F_T,
                    upward ? G_V_L - F_V_H : G_V_H - F_V_L,
                    upward ? getPercent(F_V_H, G_V_L) : getPercent(F_V_L, G_V_H),
                    upward ? getPercent(F_V_H, G_V_L, startV) : getPercent(F_V_L, G_V_H, startV)
            ));
            displayDTO.setG2H(new DataDisplayProcessVO(
                    H_T - G_T,
                    upward ? H_V_H - G_V_L : H_V_L - G_V_H,
                    upward ? getPercent(G_V_L, H_V_H) : getPercent(G_V_H, H_V_L),
                    upward ? getPercent(G_V_L, H_V_H, startV) : getPercent(G_V_H, H_V_L, startV)
            ));
        }

        return displayDTO;
    }

    private String getPercent(Integer startV, Integer endV) {
        return getPercent((double) (endV - startV), startV);
    }

    private String getPercent(Integer startV, Integer endV, Integer baseV) {
        return getPercent((double) (endV - startV), baseV);
    }

    private String getPercent(double diffV, Integer baseV) {
        return setScale(diffV / baseV * 100) + "%";
    }

    private String setScale(double f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(f);
    }
}
