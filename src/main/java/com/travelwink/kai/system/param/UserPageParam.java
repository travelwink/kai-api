package com.travelwink.kai.system.param;

import com.travelwink.kai.framework.pagination.BasePageOrderParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPageParam extends BasePageOrderParam {

    private String username;

    private Integer type;

    private Date createTime;
}
