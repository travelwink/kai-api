package com.travelwink.kai.framework.redis.service;

public interface UserCacheService {
    int incrementFailureCount(String username);

    void resetFailureCount(String username);
}
