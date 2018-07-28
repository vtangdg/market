package com.example.market.dal.dao;

import com.example.market.dal.domain.Data;

import java.util.List;

public interface DataMapper {
    int insert(Data object);

    List<Data> list();


}