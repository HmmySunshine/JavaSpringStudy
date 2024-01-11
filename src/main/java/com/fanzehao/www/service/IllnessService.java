package com.fanzehao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanzehao.www.entity.Illness;
import com.fanzehao.www.entity.PageView;
import com.fanzehao.www.mapper.IllnessKindMapper;
import com.fanzehao.www.mapper.IllnessMapper;
import com.fanzehao.www.mapper.PageViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessService extends ServiceImpl<IllnessMapper, Illness> {
    @Autowired
    private IllnessKindMapper illnessKindMapper;

    @Autowired
    private PageViewMapper pageViewMapper;
    public IPage<Illness> getIllnessPageDataByIllnessName(String illnessName, int pageSize, int curPage)
    {

        Page<Illness> illnessPage=new Page<>(pageSize,curPage);
        QueryWrapper<Illness> queryWrapper = new QueryWrapper<>();
        System.out.println(illnessName);
        queryWrapper.like(illnessName != null, "illness_name", illnessName);


        IPage<Illness> illnessIPage = this.page(illnessPage, queryWrapper);

        //读取关联数据
        List<Illness> records = illnessIPage.getRecords();

        if (records.isEmpty()) {
            System.out.println("The illness list is empty.");
        }
        else {
            System.out.println("The illness list is not empty.");
        }

        if (!records.isEmpty())
        {
            try {
                records.forEach(s->{
                    s.setKindName(illnessKindMapper.selectById(s.kindId).getName());
                    QueryWrapper<PageView> wrapper = new QueryWrapper<>();
                    wrapper.eq("id",s.id);
                    s.setPageView(pageViewMapper.selectOne(wrapper).getPageViews());
                });
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        records.forEach(System.out::println);
        return illnessIPage;
    }

    public IPage<Illness> getIllnessPageDataByIllnessId(String id, int pageSize, int curPage)
    {

        Page<Illness> illnessPage=new Page<>(pageSize,curPage);
        QueryWrapper<Illness> queryWrapper = new QueryWrapper<>();
        System.out.println(id);
        queryWrapper.eq(id != null, "id", id);


        IPage<Illness> illnessIPage = this.page(illnessPage, queryWrapper);

        //读取关联数据
        List<Illness> records = illnessIPage.getRecords();

        if (records.isEmpty()) {
            System.out.println("The illness list is empty.");
        }
        else {
            System.out.println("The illness list is not empty.");
        }

        if (!records.isEmpty())
        {
            try {
                records.forEach(s->{
                    s.setKindName(illnessKindMapper.selectById(s.kindId).getName());
                    QueryWrapper<PageView> wrapper = new QueryWrapper<>();
                    wrapper.eq("id",s.id);
                    s.setPageView(pageViewMapper.selectOne(wrapper).getPageViews());
                });
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        records.forEach(System.out::println);
        return illnessIPage;
    }

    public IPage<Illness> getIllnessPageDataByKindName(String illnessName, int pageSize, int curPage)
    {

        Page<Illness> illnessPage=new Page<>(pageSize,curPage);
        QueryWrapper<Illness> queryWrapper = new QueryWrapper<>();
        System.out.println(illnessName);
        queryWrapper.like(illnessName != null, "illness_name", illnessName);


        IPage<Illness> illnessIPage = this.page(illnessPage, queryWrapper);

        //读取关联数据
        List<Illness> records = illnessIPage.getRecords();

        if (records.isEmpty()) {
            System.out.println("The illness list is empty.");
        }
        else {
            System.out.println("The illness list is not empty.");
        }

        if (!records.isEmpty())
        {
            try {
                records.forEach(s->{
                    s.setKindName(illnessKindMapper.selectById(s.kindId).getName());
                    QueryWrapper<PageView> wrapper = new QueryWrapper<>();
                    wrapper.eq("id",s.id);
                    s.setPageView(pageViewMapper.selectOne(wrapper).getPageViews());
                });
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        records.forEach(System.out::println);
        return illnessIPage;
    }
}
