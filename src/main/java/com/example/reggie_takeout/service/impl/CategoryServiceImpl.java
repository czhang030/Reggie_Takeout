package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dao.CategoryMapper;
import com.example.reggie_takeout.dao.EmployeeMapper;
import com.example.reggie_takeout.pojo.Category;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.service.CategoryService;
import com.example.reggie_takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;



}
