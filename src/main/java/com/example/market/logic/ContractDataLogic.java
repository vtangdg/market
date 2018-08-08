package com.example.market.logic;

import com.example.market.dal.dao.ContractDataMapper;
import com.example.market.dal.dao.DataMapper;
import com.example.market.dal.domain.ContractData;
import com.example.market.dal.domain.Data;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ContractDataLogic {
    @Resource
    private ContractDataMapper contractDataMapper;

    public int insert(ContractData contractData) {
        return contractDataMapper.insert(contractData);
    }

}
