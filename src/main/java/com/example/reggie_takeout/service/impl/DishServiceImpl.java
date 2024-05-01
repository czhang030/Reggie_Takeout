package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dao.DishMapper;
import com.example.reggie_takeout.pojo.Dish;
import com.example.reggie_takeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishMapper dishMapper;

    /**
     * 查询当前分类关联菜品数
     *
     * @param id
     * @return
     */
    @Override
    public Long countRelatedCategory(Long id) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, Dish::getCategoryId, id);
        return dishMapper.selectCount(queryWrapper);
    }
}
