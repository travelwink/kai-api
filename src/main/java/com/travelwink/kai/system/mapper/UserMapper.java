package com.travelwink.kai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.travelwink.kai.framework.pagination.PageModel;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.UserPageParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE username = #{param.username} and create_time <= #{param.createTime}")
    PageModel<User> getPageList(@Param("page") IPage<?> page, @Param("param") UserPageParam userPageParam);
}
