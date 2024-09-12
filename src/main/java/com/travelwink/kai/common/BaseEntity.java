package com.travelwink.kai.common;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7176390653391227433L;

    @SchemaProperty(name = "创建人")
    private String createdBy;

    @SchemaProperty(name = "创建时间")
    @Null(message = "创建时间不用传")
    private Date createTime;

    @SchemaProperty(name = "修改人")
    private String updatedBy;

    @SchemaProperty(name = "修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;
}
