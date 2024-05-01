package com.example.reggie_takeout.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public  void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充(insert)...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        Long currentId = BaseContext.getCurrentId();
        metaObject.setValue("create_user",currentId);
        metaObject.setValue("update_user", currentId);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充(update)...");
        metaObject.setValue("updateTime", LocalDateTime.now());

        //利用BaseContext获取执行员工更新操作的管理员Id
        Long update_user = BaseContext.getCurrentId();
        metaObject.setValue("update_user", update_user);

    }
}
