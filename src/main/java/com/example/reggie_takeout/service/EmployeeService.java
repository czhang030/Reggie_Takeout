package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.pojo.Result;


public interface EmployeeService extends IService<Employee> {

    //分页获取员工信息
    public Result<Page<Employee>> getEmployeePageByName(int page,int pageSize, String name);

}
