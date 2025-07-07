package org.example.mysteryboxlearn.common.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充BaseEntity处理
 */
@Primary
@Component
@Slf4j
public class BaseEntityHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "editedTime", LocalDateTime.class, LocalDateTime.now());

        String currentUserId = getCurrenUserId();
        this.strictInsertFill(metaObject, "creator", String.class, currentUserId);
        this.strictInsertFill(metaObject, "editor", String.class, currentUserId);
        log.info("insert entity fill");

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "editedTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "editor", String.class, getCurrenUserId());
        log.info("update entity fill");
    }

    private String getCurrenUserId() {
        return "0f07d638f1bc401188d86dc650ab06a7";
    }
}

