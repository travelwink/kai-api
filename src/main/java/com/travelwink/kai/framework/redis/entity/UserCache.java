package com.travelwink.kai.framework.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@RedisHash("users")
public class UserCache {

    @Id
    private String username;

    // 连续登录失败次数
    private int consecutiveLoginFailures;

}
