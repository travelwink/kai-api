package com.travelwink.kai.framework.redis.service;

/**
 * 用户缓存Service接口
 * @author chris
 */
public interface UserCacheService {

    /**
     * 增加用户登录失败次数
     * @param username 用户名
     * @return 失败次数
     */
    int incrementFailureCount(String username);

    /**
     * 重置用户登录失败次数
     * @param username 用户名
     */
    void resetFailureCount(String username);
}
