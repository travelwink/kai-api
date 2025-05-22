package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.framework.exception.BusinessException;
import com.travelwink.kai.framework.pagination.PageResult;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.UserPageParam;

/**
 * <h5>用户服务接口</h5>
 *
 * @author Chris Liao
 */
public interface UserService extends IService<User> {

    /**
     * <h6>创建用户</h6>
     * <p>
     * 方法逻辑步骤
     * <ol>
     *     <li>验证用户名</li>
     *     <li>密码加密</li>
     *     <li>分配默认角色&权限</li>
     * </ol>
     *
     * @param user 用户实体
     * @return 创建是否成功
     */
    boolean createUser(User user) throws BusinessException;

    User getByUsername(String username);

    boolean isUsernameExists(String username);

    /**
     * <h5>分页查询</h5>
     * <p>
     * 查询条件：
     * <ul>
     *     <li>用户名(模糊匹配)</li>
     *     <li>类型</li>
     *     <li>状态</li>
     *     <li>创建时间起始日期</li>
     *     <li>创建时间结束日期</li>
     *     <li>分页信息(页码、每页大小、排序)</li>
     * </ul>
     *
     * @param userPageParam 分页查询参数
     * @return 分页查询结果
     */
    PageResult<User> getPageList(UserPageParam userPageParam);
}
