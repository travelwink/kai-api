package com.travelwink.kai.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Component
public class KaiMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object status = getFieldValByName("status", metaObject);
        if (ObjectUtils.isEmpty(status)) {
            setFieldValByName("status", 1, metaObject);
        }
        // todo 从Redis中获取用户ID
        this.strictInsertFill(metaObject, "createdBy", String.class, "init");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedBy", String.class, "init");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // todo 从Redis中获取用户ID
        this.strictUpdateFill(metaObject, "updatedBy", String.class, "init");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
