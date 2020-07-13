package com.example.market.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.market.dal.dao.FundMapper;
import com.example.market.dal.dao.FundStockDao;
import com.example.market.dal.domain.FundDO;
import com.example.market.dal.domain.FundStockDO;
import com.example.market.model.FundQuery;
import com.example.market.util.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author degang
 * @date 2020/7/12
 */
@Service
@Slf4j
public class FundService {
    @Resource
    private FundMapper fundMapper;
    @Resource
    private FundStockDao fundStockDao;

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

    public void pullDetail() {
        String format = "http://www.iwencai.com/stockpick/get-detailed-data?sc=%s&dr=%s&question_type=fund&time_type=%s&ndd=%s";
        FundQuery query = new FundQuery();
        query.setPageSize(1);
        query.setOrderBy("id asc");
        List<FundDO> list = fundMapper.fetch(query);

        list.forEach(t -> {
            try {
                int pos = t.getCode().indexOf(".");
                String url = String.format(format, t.getCode().substring(0, pos), t.getStockDay(), "%E6%8A%A5%E5%91%8A%E6%9C%9F", "%E5%9F%BA%E9%87%91%40%E9%87%8D%E4%BB%93%E8%82%A1%E6%98%8E%E7%BB%86");
                String s = HttpUtil.doGet(url, "utf-8");
                StockResponse response = JSONObject.parseObject(s, StockResponse.class);
                response.getData().getResult().forEach(e -> {
                    FundStockDO model = new FundStockDO();
                    model.setCode(e.get(0));
                    model.setRank(Byte.valueOf(e.get(2)));
                    model.setStockName(e.get(3));
                    model.setStockValue(e.get(4));
                    model.setStockName(e.get(5));
                    model.setStockChange(e.get(6));
                    model.setValueRate(new BigDecimal(e.get(7)));
                    model.setTotalValueRate(new BigDecimal(e.get(8)));
                    model.setStockValueRate(new BigDecimal(e.get(9)));
                    model.setFundId(t.getId());

                    fundStockDao.insert(model);
                });
                System.out.println(s);
            } catch (Exception e) {
                log.error("", e);
            }
        });
    }

    @Data
    private static class StockResponse {
        private StockDetail data;
    }

    @Data
    private static class StockDetail {
        private List<String> title;
        private List<List<String>> result;

    }
}
