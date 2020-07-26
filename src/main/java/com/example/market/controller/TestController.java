package com.example.market.controller;

import com.example.market.dal.dao.FundDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author degang
 * @date 2020/7/9
 */
@RestController("/test")
@ResponseBody
public class TestController {
    @Resource
    private FundDao fundDao;

    @RequestMapping("test")
    public Object list() {
        return fundDao.selectOneById(1L);
    }
}
