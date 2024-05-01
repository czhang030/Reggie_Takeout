package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dao.DishMapper;
import com.example.reggie_takeout.pojo.Dish;
import com.example.reggie_takeout.pojo.DishDto;
import com.example.reggie_takeout.pojo.DishFlavor;
import com.example.reggie_takeout.service.DishFlavorService;
import com.example.reggie_takeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorService dishFlavorService;

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

    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //将菜品数据部分保存到dish表
        this.save(dishDto);
        Long id = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();

        /**
         *  将获取到的dishId赋值给dishFlavor的dishId属性
         *  但是此时没有写入表哦
         */
        for (DishFlavor dishFlavor:flavors
             ) {
            dishFlavor.setDishId(id);
        }
        //同时将菜品口味数据保存到dish_flavor表
        dishFlavorService.saveBatch(flavors);

    }
}
