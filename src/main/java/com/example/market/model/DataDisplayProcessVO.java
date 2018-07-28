package com.example.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDisplayProcessVO {
    private Integer diffT;
    private Integer diffV;
    /*A-B阶段差值百分比，(B-A)/A */
    private String diffVP;
    /*A-B阶段差值基数百分比，起始值为O，则(B-A)/O */
    private String dffVBP;
}
