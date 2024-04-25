package com.example.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.pojo.Result;
import com.example.reggie_takeout.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //发送post请求
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(true, Employee::getUsername, employee.getUsername());

        Employee emp = employeeService.getOne(employeeLambdaQueryWrapper);
        if (null==emp){
            return Result.error("不存在此用户");
        } else if (!emp.getPassword().equals(password)) {
            return Result.error("密码错误");
        } else if (emp.getStatus()==0) {
            return Result.error("该用户已被禁用");
        }

        //Session记录下成功登录的用户ID即可
        request.getSession().setAttribute("employee", emp.getId());

        return Result.success(emp);
    }

    /**
     * 登出功能
     * @param request
     * @return
     */
    //Post请求
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        System.out.println("登出");
        return Result.success("成功退出");
    }

    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增的员工信息：{}", employee.toString());
        //设置默认密码为123456，并采用MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置createTime和updateTime
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
//        //根据session来获取创建人的id
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        //并设置
//        employee.setCreate_user(empId);
//        employee.setUpdate_user(empId);
        //存入数据库
        employeeService.save(employee);
        return Result.success("添加员工成功");
    }

//    @PostMapping
//    public Result<String> addEmployee(HttpServletRequest request,@RequestBody Employee employee){
//        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        Long empId = (Long)request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
//
//        employeeService.save(employee);
//        return Result.success("添加员工成功");
//    }

    //分页获取员工信息
    @GetMapping("/page")
    public Result<Page<Employee>> page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("name") String name) {
        log.info("page={},pageSize={},name={}", page, pageSize, name);

        return employeeService.getEmployeePageByName(page, pageSize, name);
    }

}
