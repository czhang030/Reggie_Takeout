package com.example.reggie_takeout.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 在某些情况下，前端页面提交的数据可能并不完全符合后端实体类的结构，此时可以使用DTO来接收前端传来的数据，并在服务层中进行相应的处理和转换，以满足后续业务逻辑的需要。
 * 看到extends也可以理解了
 */
@Data
public class DishDto extends Dish{
    private List<DishFlavor> flavors= new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
