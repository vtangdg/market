package com.example.market.dal.dao;

import com.example.market.dal.domain.ContractData;
import com.example.market.dal.domain.Data;

import java.util.List;

public interface ContractDataMapper {
    int insert(ContractData object);

    List<ContractData> list();
}