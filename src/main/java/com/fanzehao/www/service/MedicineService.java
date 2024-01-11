package com.fanzehao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanzehao.www.entity.Illness;
import com.fanzehao.www.entity.Medicine;

import com.fanzehao.www.entity.PageView;
import com.fanzehao.www.mapper.IllnessKindMapper;
import com.fanzehao.www.mapper.MedicineMapper;
import com.fanzehao.www.mapper.PageViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService extends ServiceImpl<MedicineMapper, Medicine> {
    @Autowired
    protected MedicineMapper medicineMapper;
    @Autowired
    private PageViewMapper pageViewMapper;
    public Medicine findMedicineByName(String medicineName)
    {

        QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("medicine_name", medicineName);
        return  this.getOne(queryWrapper);
    }



    public IPage<Medicine> getMedicinePageDataByMedicineName(String medicineName, int pageSize, int curPage)
    {

        Page<Medicine> medicinePage=new Page<>(pageSize,curPage);
        QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
        System.out.println(medicineName);
        queryWrapper.like(medicineName != null, "medicine_name", medicineName);


        IPage<Medicine> medicineIPage = this.page(medicinePage, queryWrapper);

        //读取关联数据
        List<Medicine> records = medicinePage.getRecords();

        if (records.isEmpty()) {
            System.out.println("The medicine list is empty.");
        }
        else {
            System.out.println("The medicine list is not empty.");
        }

        if (!records.isEmpty())
        {
            try {
                records.forEach(s->{
                    s.setMedicineName(medicineMapper.selectById(s.getId()).getMedicineName());
                    QueryWrapper<PageView> wrapper = new QueryWrapper<>();
                    wrapper.eq("id",s.getId());
                    s.setPageView(pageViewMapper.selectOne(wrapper).getPageViews());
                });
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        records.forEach(System.out::println);
        return medicineIPage;
    }
}
