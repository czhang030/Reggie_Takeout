package com.example.reggie_takeout.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Data
@TableName("db_reggie.employee")
public class Employee {

  @TableId(type = IdType.ASSIGN_ID)
  private int id;
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
  private Long create_user;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long update_user;
}
