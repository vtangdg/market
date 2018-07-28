package com.example.market.api;

import com.example.market.dal.domain.Data;
import com.example.market.model.PageForm;
import com.example.market.model.DataDisplayDTO;

import java.util.List;

public interface DataService {
    void saveData(Data data);

    List<DataDisplayDTO> listData();

    PageForm<List<DataDisplayDTO>> listDataByPage();
}
