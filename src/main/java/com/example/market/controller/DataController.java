package com.example.market.controller;

import com.example.market.api.ContractDataService;
import com.example.market.api.DataService;
import com.example.market.api.FileService;
import com.example.market.model.ContractDataForm;
import com.example.market.model.DataDisplayDTO;
import com.example.market.model.DataForm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping
public class DataController {
    @Autowired
    private DataService dataService;
    @Autowired
    private ContractDataService contractDataService;
    @Autowired
    private FileService fileService;

    @GetMapping("data")
    public String toDataCollect(Model model) {
        model.addAttribute("day", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        return "dataCollect";
    }

    @PostMapping("data")
    public String saveData(DataForm form) {
        if (!form.getFile().isEmpty()) {
            String fileName = fileService.save(form.getFile(), form.getDate().toString());
            form.setImageUrl("image" + fileName);
        }

        Integer id = dataService.saveData(form.toData());
        if (form.getContractDataInput() != null && form.getContractDataInput()) {
            return String.format("redirect:/contractData?dataId=%s&date=%s", id, form.getDate());
        }
        return "redirect:/data";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }
    @PostMapping("test")
    public String test(MultipartFile file) {
        fileService.save(file, "20180822");
        return "redirect:/data";
    }

    @GetMapping("contractData")
    public String toContractDataCollect(@RequestParam Integer dataId, @RequestParam Integer date, Model model) {
        model.addAttribute("dataId", dataId);
        model.addAttribute("day", date);
        return "contractDataCollect";
    }
    @PostMapping("contractData")
    public String saveContractData(ContractDataForm form) {
        contractDataService.saveData(form.toData());
        return "redirect:/data";
    }


    @GetMapping("listDisplayData")
    public String listDisplayData(Model model) {
        List<DataDisplayDTO> dtos = dataService.listData();
        model.addAttribute("items", dtos);
        return "dataDisplayList";
    }


}
