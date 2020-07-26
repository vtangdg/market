package com.example.market.logic;

import com.example.market.BaseTest;
import com.example.market.dal.dao.FundDao;
import com.example.market.dal.domain.FundDO;
import com.example.market.model.FundQuery;
import com.example.market.service.FundService;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author degang
 * @date 2020/7/9
 */
public class FundTest extends BaseTest {
    @Resource
    private FundDao fundDao;
    @Resource
    private FundService fundService;

    @Test
    public void select() {
        FundDO fundDO = fundDao.selectOneById(1L);
        System.out.println(fundDO);
    }

    @Test
    public void pullDetail() {
        FundQuery query = new FundQuery();
        query.setOrderBy("id desc");
        query.setPageSize(1);

        List<FundDO> list = fundDao.fetch(query);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        fundService.pullDetail(list.get(0).getQueryDay(), null);
    }
}
