package com.example.market.service;

import com.example.market.dal.dao.FundMapper;
import com.example.market.dal.domain.FundDO;
import com.example.market.model.FundQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author degang
 * @date 2020/7/12
 */
@Service
public class FundService {
    @Resource
    private FundMapper fundMapper;

    public Map countStock() {
        FundQuery query = new FundQuery();
        query.setPageSize(5000);
        List<FundDO> list = fundMapper.fetch(query);

        Map<String, Integer> resMap = new HashMap<>();
        list.stream().map(FundDO::getHeavyStock).forEach(t -> {
            String[] split = t.split("\\|\\|");
            String key = split[0];
            resMap.put(key, resMap.get(key) == null ? 1 : resMap.get(key) + 1);
        });

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(resMap.entrySet());
        entryList.sort(((o1, o2) -> o2.getValue() - o1.getValue()));

        Map<String, Integer> linkMap = new LinkedHashMap<>();
        entryList.forEach(t -> {
            linkMap.put(t.getKey(), t.getValue());
        });

        return linkMap;
    }
}
