package com.fanzehao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanzehao.www.entity.Medicine;

import com.fanzehao.www.mapper.MedicineMapper;
import org.springframework.stereotype.Service;

@Service
public class MedicineService extends ServiceImpl<MedicineMapper, Medicine> {
    public Medicine findMedicineByName(String medicineName)
    {
        QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("medicine_name", medicineName);
        return  this.getOne(queryWrapper);
    }
}
