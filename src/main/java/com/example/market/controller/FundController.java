package com.example.market.controller;

import com.example.market.model.FundStockAnalysisDTO;
import com.example.market.service.ExcelService;
import com.example.market.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author degang
 * @date 2020/7/12
 */
@Controller
@RequestMapping("/fund")
public class FundController {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private FundService fundService;

    @RequestMapping("index")
    public String index() {
        return "fund";
    }

    @RequestMapping("import")
    @ResponseBody
    public String importData(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream iStream = file.getInputStream();
        String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
        // 通过扩展名校验文件类型
        String suffix = null;
        int stuffPos = fileName.lastIndexOf(".");
        if (stuffPos != -1) {
            suffix = fileName.substring(stuffPos + 1).toLowerCase();
        }
        if (StringUtils.isEmpty(suffix) || (!suffix.equals("xls") && !suffix.equals("xlsx"))) {
            throw new IllegalArgumentException("只支持xls或xlsx格式文件");
        }
        excelService.importExcel(iStream, fileName.substring(0, stuffPos));
        return "success";
    }

    @RequestMapping("countStock")
    @ResponseBody
    public Object countStock(@RequestParam(name = "queryDay", required = false) String queryDay) {
        return fundService.countStock(queryDay);
    }

    @RequestMapping("getStockData")
    @ResponseBody
    public Object getStockData() {
        return fundService.getStockData();
    }

    @RequestMapping("stockDataTable")
    public String stockDataTable(@RequestParam(name = "queryDay", required = false) String queryDay, Model model) {
        List<FundStockAnalysisDTO> list = fundService.listStockData(queryDay);
        model.addAttribute("items", list);
        return "stockDisplayList";
    }

}
