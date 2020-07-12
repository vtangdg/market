package com.example.market.logic;

import com.example.market.BaseTest;
import com.example.market.dal.domain.Data;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class DataLogicTest extends BaseTest {
    @Autowired
    private DataLogic dataLogic;

    @Test
    public void insert() {
        Data data =  Data.builder()
                .circleT(60)
                .circleDiffV(100)
                .build();
        int res = dataLogic.insert(data);
        assert res ==1;
    }

    @Test
    public void list() {
        List<Data> dataList = dataLogic.listData();
        assert dataList.size() > 0;
    }
}