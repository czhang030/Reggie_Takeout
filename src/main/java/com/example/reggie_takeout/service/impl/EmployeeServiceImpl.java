package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dao.EmployeeMapper;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.pojo.Result;
import com.example.reggie_takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Result<Page<Employee>> getEmployeePageByName(int page, int pageSize, String name) {

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();

        //name为空即查询所有
        wrapper.like(!(null==name||"".equals(name)), Employee::getName, name);
        wrapper.orderByDesc(Employee::getUpdateTime);

        //所查的最终结果
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        employeeMapper.selectPage(pageInfo,wrapper);
        return Result.success(pageInfo);
    }

    @Override
    public Result<String> updateEmployee(Long id, Employee employee) {

        employee.setUpdate_user(id);
        employee.setUpdateTime(LocalDateTime.now());

        employeeMapper.updateById(employee);
        return Result.success("修改员工信息成功！");
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.selectById(id);

        return employee;
    }
}
