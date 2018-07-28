package com.example.market.service;

import com.example.market.api.DataService;
import com.example.market.dal.domain.Data;
import com.example.market.model.DataDisplayDTO;
import com.example.market.model.PageForm;
import com.example.market.logic.DataLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private DataLogic dataLogic;

    @Override
    public void saveData(Data data) {

        dataLogic.insert(data);
    }



    @Override
    public List<DataDisplayDTO> listData() {
        List<Data> dataList = dataLogic.listData();
        return dataList.stream().map(Data::toDataDisplayDTO).collect(Collectors.toList());
    }

    @Override
    public PageForm<List<DataDisplayDTO>> listDataByPage() {
        return null;
    }
}
