package com.example.market.controller;

import com.example.market.dal.dao.FundMapper;
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
    private FundMapper fundMapper;

    @RequestMapping("test")
    public Object list() {
        return fundMapper.selectOneById(1L);
    }
}
