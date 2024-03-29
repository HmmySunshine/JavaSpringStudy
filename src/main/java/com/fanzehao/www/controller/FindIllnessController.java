package com.fanzehao.www.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanzehao.www.entity.Illness;
import com.fanzehao.www.entity.Medicine;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.service.IllnessService;
import com.fanzehao.www.service.MedicineService;
import com.fanzehao.www.util.Assert;
import com.fanzehao.www.util.RespResult;
import com.fanzehao.www.vo.request.FeedBackRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
public class FindIllnessController {
    @Autowired
    private IllnessService illnessService;

    @Autowired
    private MedicineService medicineService;

    @RequestMapping("/findIllness")
    public String FindIllness(Model model, String illnessName,@RequestParam(required = false) String kindName,
                              @RequestParam(defaultValue = "1") Integer page)
    {
          System.out.println(kindName);
          IPage<Illness> illnessPageDataByIllnessName = illnessService.getIllnessPageDataByIllnessName(illnessName, page, 10);
          model.addAttribute("illness",illnessPageDataByIllnessName.getRecords());
          System.out.println("执行find页面");

          return "search-illness";
    }



    // <a th:href="@{findIllnessOne(id=${id})}" class="cl-blue" th:text="${illness.illnessName}"> Great Services Provided to Help You Take
    //                                Control </a>
    @RequestMapping("/findIllnessOne")
    public String FindIllnessOne(Model model, @RequestParam String id,
                                 @RequestParam(defaultValue = "1") Integer page)
    {

        System.out.println(id);

        IPage<Illness> illnessPageDataByIllnessName = illnessService.getIllnessPageDataByIllnessId(id, page, 10);

        model.addAttribute("illness",illnessPageDataByIllnessName.getRecords());
        System.out.println("执行find页面");

        return "search-illness";
    }






}
