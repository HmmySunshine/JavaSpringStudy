package com.fanzehao.www.controller;

import com.fanzehao.www.entity.Medicine;
import com.fanzehao.www.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FindMedicineController {
    @Autowired
    private MedicineService medicineService;
    @RequestMapping("/findMedicines")
    public String findMedicine(Model model, String nameValue) {

        model.addAttribute("medicine", medicineService.findMedicineByName(nameValue));
        return "illness";
    }

}
