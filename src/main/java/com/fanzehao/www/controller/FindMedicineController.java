package com.fanzehao.www.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanzehao.www.entity.Medicine;
import com.fanzehao.www.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindMedicineController {
    @Autowired
    private MedicineService medicineService;
    @RequestMapping("/findMedicines")
    public String findMedicine(Model model, String nameValue,@RequestParam(defaultValue = "1") Integer page) {
        IPage<Medicine> medicinePageDataByMedicineName = medicineService.getMedicinePageDataByMedicineName(nameValue, page, 10);
        model.addAttribute("medicine", medicinePageDataByMedicineName.getRecords());
        return "illness";
    }

}
