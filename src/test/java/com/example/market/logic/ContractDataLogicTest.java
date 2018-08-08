package com.example.market.logic;

import com.example.market.BaseTest;
import com.example.market.dal.domain.ContractData;
import com.example.market.dal.domain.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContractDataLogicTest extends BaseTest{
    @Autowired
    private ContractDataLogic contractDataLogic;

    @Test
    public void insertTest() {
        ContractData contractData =  ContractData.builder()
                .circleT(60)
                .circleDiffV(100)
                .build();
        int res = contractDataLogic.insert(contractData);
        assert res ==1;
    }

}