package com.travelwink.kai.framework.redis.service.impl;

import com.travelwink.kai.framework.redis.entity.UserCache;
import com.travelwink.kai.framework.redis.repository.UserCacheRepository;
import com.travelwink.kai.framework.redis.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    UserCacheRepository userCacheRepository;

    public int incrementFailureCount(String username) {
        UserCache userCache = userCacheRepository.findById(username)
                .orElseGet(() -> new UserCache(username, 0));
        userCache.setConsecutiveLoginFailures(userCache.getConsecutiveLoginFailures() + 1);
        if (userCache.getConsecutiveLoginFailures() < 15) {
            userCacheRepository.save(userCache);
        }
        return userCache.getConsecutiveLoginFailures();
    }

    @Override
    public void resetFailureCount(String username) {
        UserCache userCache = userCacheRepository.findById(username)
                .orElseGet(() -> new UserCache(username, 0));
        userCache.setConsecutiveLoginFailures(0);
        userCacheRepository.save(userCache);
    }

}
