package com.example.market.service;

import com.example.market.api.ContractDataService;
import com.example.market.api.DataService;
import com.example.market.dal.domain.ContractData;
import com.example.market.dal.domain.Data;
import com.example.market.logic.ContractDataLogic;
import com.example.market.logic.DataLogic;
import com.example.market.model.DataDisplayDTO;
import com.example.market.model.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractDataServiceImpl implements ContractDataService {
    @Autowired
    private ContractDataLogic contractDataLogic;

    @Override
    public void saveData(ContractData data) {

        contractDataLogic.insert(data);
    }




}
