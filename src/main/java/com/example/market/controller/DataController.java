package com.example.market.controller;

import com.example.market.api.DataService;
import com.example.market.model.DataDisplayDTO;
import com.example.market.model.DataForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @PostMapping("save")
    public String saveData(DataForm form) {

        dataService.saveData(form.toData());
        return "redirect:/data/save";
    }


    @GetMapping("/listDisplayData")
    public String listDisplayData(Model model) {
        List<DataDisplayDTO> dtos = dataService.listData();
        model.addAttribute("items", dtos);
        return "list";
    }

    @GetMapping("save")
    public String toDataCollect(Model model) {
        model.addAttribute("day", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        return "dataCollect";
    }

}
