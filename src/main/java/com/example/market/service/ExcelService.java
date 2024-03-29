package com.example.market.service;

import com.example.market.dal.dao.FundDao;
import com.example.market.dal.domain.FundDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author degang
 * @date 2020/7/12
 */
@Service
@Slf4j
public class ExcelService {
    @Resource
    private FundDao fundDao;

    /**
     * @param inputStream 导入数据
     * @return 结构
     * @throws Exception 异常
     */
    public void importExcel(InputStream inputStream, String fileName){
        // 1:获取数据dds
        List<Map<Integer, String>> excelList = readExcel(inputStream);
        if (excelList == null || excelList.size() < 1) {
            return;
        }
        int columnStock = 8;
        String stockDay = specialDayExtract(excelList.get(0).get(columnStock), 0).replace(".", "-");
        String cxDay = specialDayExtract(excelList.get(0).get(6), 1);
        excelList.remove(0);
        List<FundDO> collect = excelList.stream().map(t -> {
            // 0-基金代码、1-基金简称、5-5年增长、6-晨星3年评级、7-晨星5年评级、8-重仓股票、11-前10重仓占净值比
            FundDO model = new FundDO();
            model.setCode(t.get(0));
            model.setName(t.get(1));
            model.setIncrY5(new BigDecimal(save2Zero(t.get(5))));
            model.setCxL3(Byte.valueOf(t.get(6).substring(0, 1)));
            if (!NumberUtils.isDigits(t.get(7).substring(0, 1))) {
                log.warn(t.get(7));
                model.setCxL5((byte)-1);
            } else {
                model.setCxL5(Byte.valueOf(t.get(7).substring(0, 1)));
            }
            model.setHeavyStock(t.get(columnStock));
            String stockRatioStr = t.get(10);;
            if (NumberUtils.isCreatable(stockRatioStr)) {
                model.setStockRatio(new BigDecimal(stockRatioStr));
            } else {
                model.setStockRatio(BigDecimal.ZERO);
            }
            model.setCxDay(cxDay);
            model.setStockDay(stockDay);
            model.setQueryDay(fileName);

            return model;
        }).collect(Collectors.toList());

        fundDao.batchInsert(collect);
    }

    public static void main(String[] args) {
        //
        // 基金十大重仓股票2020.03.31
        String s = "晨星3年评级(星)2020.06.30";
        StringBuffer sb = new StringBuffer();
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') {
                sb.append(c);
            }
        }
        System.out.println(sb.toString().substring(1));
    }

    private String specialDayExtract(String text, int subBeginIndex) {
        try {
            StringBuffer sb = new StringBuffer();
            for (char c : text.toCharArray()) {
                if ((c >= '0' && c <= '9') || c == '.') {
                    sb.append(c);
                }
            }
            return (sb.toString().substring(subBeginIndex));

        } catch (Exception e) {
            log.error("text:{}", text, e);
        }
        return "";
    }

    /**
     * 将excel内容转换成List
     * 支持xls格式
     * @param is 流
     * @return list
     */
    public List<Map<Integer, String>> readExcel(InputStream is) {
        List<Map<Integer, String>> varList = new ArrayList<>();
        // 从数据行开始
        byte startRow = 0;
        byte startCol = 0;
        byte sheetNum = 0;
        try {
            Workbook workbook = new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(sheetNum);
            int rowNum = sheet.getLastRowNum() + 1;

            for(int i = startRow; i < rowNum; ++i) {
                HashMap<Integer, String> varMap = new HashMap<>();
                Row row = sheet.getRow(i);
                short cellNum = row.getLastCellNum();

                for(int j = startCol; j < cellNum; ++j) {
                    Cell cell = row.getCell(j);
                    String cellValue;
                    if(null != cell) {
                        switch(cell.getCellType()) {
                            case 0:
                                cellValue = String.valueOf(cell.getNumericCellValue());
                                break;
                            case 1:
                                cellValue = StringUtils.trim(cell.getStringCellValue());
                                break;
                            case 2:
                                cellValue = cell.getNumericCellValue() + "";
                                break;
                            case 3:
                                cellValue = "";
                                break;
                            case 4:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case 5:
                                cellValue = String.valueOf(cell.getErrorCellValue());
                                break;
                            default:
                                cellValue = "";
                        }
                    } else {
                        cellValue = "";
                    }
                    varMap.put(j, cellValue);
                }
                // 防止excel有空行(若前两列为空则为空行)
                if (!StringUtils.isEmpty(varMap.get((int)startCol)) || !StringUtils.isEmpty(varMap.get((int)startCol + 1))) {
                    varList.add(varMap);
                }

            }
        } catch (Exception e) {
            log.error("", e);
        }

        return varList;
    }

    private static String save2Zero(String price) {
        if (StringUtils.isEmpty(price)) {
            return price;
        }
        List<String> strings = Arrays.asList(price.split("\\."));
        if (strings.size() < 2) {
            return price + ".00";
        }
        if (strings.get(1).length() < 2) {
            return strings.get(0) + "." + strings.get(1) + "0";
        } else if (strings.get(1).length() == 2) {
            return price;
        } else {
            return strings.get(0) + "." + strings.get(1).substring(0, 2);
        }
    }
}
