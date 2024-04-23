package com.example.reggie_takeout;

import com.example.reggie_takeout.dao.EmployeeMapper;
import com.example.reggie_takeout.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class ReggieTakeoutApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
//        Employee employee = employeeMapper.selectById(0);
//        System.out.println(employee);

        Employee employee = new Employee();
        employee.setName("蔡徐坤");
        employee.setUsername("ikun");
        employee.setCreate_user(1L);
        employee.setUpdate_user(1L);
        employee.setPassword("5201314");
        employee.setPhone("15811234568");
        employee.setIdNumber("111112222224444");
        employee.setSex("男");
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        log.info("新增员工的id: {}",employee.getId());
        employeeMapper.insert(employee);
    }

}
