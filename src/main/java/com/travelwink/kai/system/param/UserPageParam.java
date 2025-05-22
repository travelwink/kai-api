package com.travelwink.kai.system.param;

import com.travelwink.kai.framework.enums.AccountStatus;
import com.travelwink.kai.framework.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPageParam extends BasePageOrderParam {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "状态")
    private AccountStatus status;

    @Schema(description = "创建时间起始日期")
    private Date createTimeStart;

    @Schema(description = "创建时间结束日期")
    private Date createTimeEnd;

}
