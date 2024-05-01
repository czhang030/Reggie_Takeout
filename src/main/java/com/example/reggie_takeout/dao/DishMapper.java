package com.example.reggie_takeout.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie_takeout.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
