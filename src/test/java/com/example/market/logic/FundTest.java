package com.example.market.logic;

import com.example.market.BaseTest;
import com.example.market.dal.dao.FundMapper;
import com.example.market.dal.domain.FundDO;
import com.example.market.service.FundService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author degang
 * @date 2020/7/9
 */
public class FundTest extends BaseTest {
    @Resource
    private FundMapper fundMapper;
    @Resource
    private FundService fundService;

    @Test
    public void select() {
        FundDO fundDO = fundMapper.selectOneById(1L);
        System.out.println(fundDO);
    }

    @Test
    public void pullDetail() {
        fundService.pullDetail();
    }
}
