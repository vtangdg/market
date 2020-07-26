package com.example.market.service;

import com.alibaba.fastjson.JSONObject;
import com.example.market.dal.dao.FundDao;
import com.example.market.dal.dao.FundStockDao;
import com.example.market.dal.domain.FundDO;
import com.example.market.dal.domain.FundStockDO;
import com.example.market.model.FundQuery;
import com.example.market.model.FundStockAnalysisDTO;
import com.example.market.model.FundStockQuery;
import com.example.market.util.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author degang
 * @date 2020/7/12
 */
@Service
@Slf4j
public class FundService {
    List<String> NOT_SHOW_STOCK = Arrays.asList(
            "贵州茅台,600519",
            "立讯精密,002475",
            "五粮液,000858"
            );
    private final boolean EXCLUDE_START_3 = true;

    @Resource
    private FundDao fundDao;
    @Resource
    private FundStockDao fundStockDao;

    public Map countStock(String queryDay) {
        List<Map.Entry<String, List<FundStockAnalysisDTO>>> entries = listStockDataMap(queryDay);
        Map<String, Integer> resMap = new LinkedHashMap<>();
        entries.forEach(t -> {
            resMap.put(t.getKey(), t.getValue().size());
        });
        return resMap;
    }

    public Map getStockData() {
        FundStockQuery query = new FundStockQuery();
        query.setPageSize(50000);
        List<FundStockDO> list = fundStockDao.fetch(query);
        Map<String, List<FundStockDO>> collect = list.stream().collect(Collectors.groupingBy(FundStockDO::getStockName));
        ArrayList<Map.Entry<String, List<FundStockDO>>> entryList = new ArrayList<>(collect.entrySet());
        entryList.sort(((o1, o2) -> o2.getValue().size() - o1.getValue().size()));

        Map<String, List<FundStockAnalysisDTO>> linkMap = new LinkedHashMap<>();
        entryList.forEach(t -> {
            linkMap.put(t.getKey(),
                    t.getValue().stream()
                            .map(this::convert)
                            .sorted(Comparator.comparingLong(FundStockAnalysisDTO::getStockRMB).reversed())
                            .collect(Collectors.toList()));
        });

        return linkMap;
    }

    public List<Map.Entry<String, List<FundStockAnalysisDTO>>> listStockDataMap(String queryDay) {
        if (queryDay == null) {
            FundDO latestRecord = getLatestRecord();
            if (latestRecord == null) {
                return new ArrayList<>();
            }
            queryDay = latestRecord.getQueryDay();
        }


        List<FundStockAnalysisDTO> list = fundDao.listAnalysisData(queryDay);
        list.forEach(t -> t.setStockRMB(parseStockValue(t.getStockValue())));
        Map<String, List<FundStockAnalysisDTO>> collect = list.stream()
                .filter(t -> {
                    if (NOT_SHOW_STOCK.contains(t.getStockName())) {
                        return false;
                    }
                    if (EXCLUDE_START_3 && t.getStockName().contains(",3")) {
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.groupingBy(FundStockAnalysisDTO::getStockName));
        ArrayList<Map.Entry<String, List<FundStockAnalysisDTO>>> entryList = new ArrayList<>(collect.entrySet());
        entryList.sort(((o1, o2) -> o2.getValue().size() - o1.getValue().size()));
        entryList.forEach(t -> {
            t.getValue().sort(Comparator.comparingLong(FundStockAnalysisDTO::getStockRMB).reversed());
        });

        return entryList;
    }

    public List<FundStockAnalysisDTO> listStockData(String queryDay) {
        List<Map.Entry<String, List<FundStockAnalysisDTO>>> entryList = listStockDataMap(queryDay);
        List<FundStockAnalysisDTO> resList = new ArrayList<>();
        entryList.forEach(t -> resList.addAll(t.getValue()));
        return resList;
    }

    private FundStockAnalysisDTO convert(FundStockDO model) {
        FundStockAnalysisDTO dto = new FundStockAnalysisDTO();
        dto.setStockRMB(parseStockValue(model.getStockValue()));
        dto.setStockValue(model.getStockValue());
        dto.setStockNum(model.getStockNum());
        dto.setStockChange(model.getStockChange());
        return dto;
    }

    private Long parseStockValue(String stockValue) {
        if (stockValue.indexOf('万') != -1 ) {
            return new BigDecimal(stockValue.substring(0, stockValue.length() - 1)).multiply(BigDecimal.valueOf(10000L)).longValue();
        }
        if (stockValue.indexOf('亿') != -1) {
            return new BigDecimal(stockValue.substring(0, stockValue.length() - 1)).multiply(BigDecimal.valueOf(1_000_000_000L)).longValue();
        }

        return Long.valueOf(stockValue);
    }

    public void pullDetail(String queryDay, List<Long> ids) {
        String format = "http://www.iwencai.com/stockpick/get-detailed-data?sc=%s&dr=%s&question_type=fund&time_type=%s&ndd=%s";
        FundQuery query = new FundQuery();
        query.setPageSize(1000);
        query.setOrderBy("id asc");
        query.setQueryDay(queryDay);
        query.setIds(ids);
        List<FundDO> list = fundDao.fetch(query);
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("无数据");
        }

        list.forEach(t -> {
            try {

                FundStockQuery stockQuery = new FundStockQuery();
                stockQuery.setFundId(t.getId());
                stockQuery.setPageSize(1);
                List<FundStockDO> fundStockDOList = fundStockDao.fetch(stockQuery);
                if (!CollectionUtils.isEmpty(fundStockDOList)) {
                    return;
                }

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
                    model.setStockNum(e.get(5));
                    model.setStockChange(e.get(6));
                    model.setValueRate(new BigDecimal(e.get(7)));
                    model.setTotalValueRate(new BigDecimal(e.get(8)));
                    model.setStockValueRate(new BigDecimal(e.get(9)));
                    model.setFundId(t.getId());

                    fundStockDao.insert(model);
                });
                Thread.sleep(500);
            } catch (Exception e) {
                log.error("", e);
            }
        });
    }

    FundDO getLatestRecord() {
        FundQuery query = new FundQuery();
        query.setPageSize(1);
        query.setOrderBy("id desc");
        List<FundDO> list = fundDao.fetch(query);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
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
