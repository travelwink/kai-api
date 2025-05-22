package com.travelwink.kai.framework.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "账号状态枚举")
public enum AccountStatus implements IEnum<Integer> {

    @Schema(description = "禁用")
    DISABLED(0),

    @Schema(description = "启用")
    ENABLED(1),

    @Schema(description = "锁定")
    LOCKED(2);

    @EnumValue
    private final Integer value;

    @Override
    @JsonValue
    public Integer getValue() {
        return value;
    }

    @JsonCreator
    public static AccountStatus fromValue(int value) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的用户状态值: " + value);
    }
}
