package com.example.reggie_takeout.controller;

import com.example.reggie_takeout.pojo.DishDto;
import com.example.reggie_takeout.pojo.Result;
import com.example.reggie_takeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    Result<String> saveDish(@RequestBody DishDto dishDto){

        dishService.saveWithFlavor(dishDto);

        return Result.success("新增菜品成功！");
    }
}
