package com.travelwink.kai.framework.redis.repository;

import com.travelwink.kai.framework.redis.entity.UserCache;
import org.springframework.data.repository.CrudRepository;

public interface UserCacheRepository extends CrudRepository<UserCache, String> {
}
