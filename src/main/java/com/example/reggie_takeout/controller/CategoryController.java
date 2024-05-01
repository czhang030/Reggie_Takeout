package com.example.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie_takeout.pojo.Category;
import com.example.reggie_takeout.pojo.Result;
import com.example.reggie_takeout.service.CategoryService;
import com.example.reggie_takeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishService dishService;

    @PostMapping
    public Result<String> saveCategory(@RequestBody Category category){
        log.info("新增的category:{}",category);
        categoryService.save(category);
        return Result.success(category.getType()==1?"新增菜品分类成功":"新增套餐分类成功");
    }

    /**
     * 查询商品套餐分页
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){

        Page<Category> pageInfo = new Page<>();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //分页查询的按照sort降序排序
        queryWrapper.orderByDesc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    @DeleteMapping
    private Result<String> delete(Long id) {
        log.info("将被删除的id：{}", id);
        Long count1 = dishService.countRelatedCategory(id);

        categoryService.removeById(id);
        return Result.success("分类信息删除成功");
    }

   @PutMapping
    Result<String> updateCategory(@RequestBody Category category){
        log.info("修改分类信息为：{}", category);
        categoryService.updateById(category);
        return Result.success("分类更新成功");
   }


}
