package com.example.market.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDisplayDTO {
    private DataDisplayProcessVO A2B;
    private DataDisplayProcessVO B2C;
    private DataDisplayProcessVO C2D;
    private DataDisplayProcessVO D2E;
    private DataDisplayProcessVO E2F;
    private DataDisplayProcessVO F2G;
    private DataDisplayProcessVO G2H;

    private Integer circleT;
    private Integer circleDiffV;
    private String circleDiffVP;
    private Integer date;
    private Integer startV;
    private Integer endV;
    private String imageUrl;
    private String remark;
}
