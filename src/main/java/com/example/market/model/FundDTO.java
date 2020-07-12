package com.example.market.model;

import com.example.market.dal.domain.FundDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* 基金信息表-DTO
*
* @author 
* @date 2020-07-09
*/
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class FundDTO extends FundDO {
}
