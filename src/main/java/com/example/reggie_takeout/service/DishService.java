package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.pojo.Dish;

public interface DishService extends IService<Dish> {
    public Long countRelatedCategory(Long id);
}
