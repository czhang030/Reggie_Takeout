package com.example.reggie_takeout.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie_takeout.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
