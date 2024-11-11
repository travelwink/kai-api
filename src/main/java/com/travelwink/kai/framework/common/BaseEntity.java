package com.travelwink.kai.framework.common;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelwink.kai.framework.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7176390653391227433L;

    @JsonIgnore
    @Schema(description = "ID", minLength = 32)
    @NotBlank(message = "ID不能为空", groups = {Update.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @JsonIgnore
    @Schema(description = "创建人")
    @Null(message = "创建人不用传")
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @JsonIgnore
    @Schema(description = "创建时间")
    @Null(message = "创建时间不用传")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonIgnore
    @Schema(description = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @JsonIgnore
    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @JsonIgnore
    @Schema(description ="逻辑删除;0：未删除，1：已删除", defaultValue = "0")
    @Null(message = "逻辑删除不用传")
    @TableLogic
    private Integer deleted;

}
