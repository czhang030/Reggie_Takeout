package com.example.reggie_takeout.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("db_reggie.employee")
public class Employee {

  private long id;
  private String name;
  private String username;
  private String password;
  private String phone;
  private String sex;
  private String idNumber;
  private long status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  //这两个先不用管，后面再说
  @TableField(fill = FieldFill.INSERT)
  private Long createUser;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;
}
