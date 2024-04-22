package com.example.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.reggie_takeout.pojo.Employee;
import com.example.reggie_takeout.pojo.Result;
import com.example.reggie_takeout.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/employee")
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


    /**
     * 登入功能
     * @param request
     * @param employee
     * @return
     */
    //发送post请求
//    @PostMapping("/login")
//    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
//        String password = employee.getPassword();
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        //这部分就是MP
//        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(Employee::getUsername, employee.getUsername());
//        Employee emp = employeeService.getOne(lqw);
//        if (emp == null) {
//            return Result.error("登陆失败");
//        }
//        if (!emp.getPassword().equals(password)) {
//            return Result.error("登录失败");
//        }
//        if (emp.getStatus() == 0) {
//            return Result.error("该用户已被禁用");
//        }
//        //存个Session，只存个id就行了
//        request.getSession().setAttribute("employee",emp.getId());
//        return Result.success(emp);
//    }

}
