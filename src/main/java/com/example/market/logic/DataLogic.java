package com.example.market.logic;

import com.example.market.dal.dao.DataMapper;
import com.example.market.dal.domain.Data;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DataLogic {
    @Resource
    private DataMapper dataMapper;

    public int insert(Data data) {
        return dataMapper.insert(data);
    }

    public List<Data> listData() {
        return dataMapper.list();
    }
}
