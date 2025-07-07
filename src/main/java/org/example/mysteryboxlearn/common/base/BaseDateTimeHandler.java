package org.example.mysteryboxlearn.common.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

//自动填充处理器
@Slf4j
@Component
public class BaseDateTimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "editedTime", LocalDateTime.class, LocalDateTime.now());
        log.info("insert time fill");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "editedTime", LocalDateTime.class, LocalDateTime.now());
        log.info("update time fill");
    }
}
