package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.pojo.Result;


public interface EmployeeService extends IService<Employee> {

    //分页获取员工信息
    public Result<Page<Employee>> getEmployeePageByName(int page,int pageSize, String name);


    /**
     * 更新员工信息
     * @param id  修改本员工信息的管理员id
     * @param employee 新的员工信息
     * @return
     */
    public Result<String> updateEmployee(Long id, Employee employee);

    /**
     * 根据传入的id查询员工
     * @param id
     * @return
     */
    public Employee getById(Long id);
}
