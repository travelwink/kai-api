package com.travelwink.kai.framework.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "分页查询基础参数")
public class BasePageParam implements Serializable {

    @Schema(description = "页码", defaultValue = "1", example = "1")
    private Long pageIndex = PageConstant.DEFAULT_PAGE_INDEX;

    @Schema(description = "每页显示数量", defaultValue = "10", example = "10")
    private Long pageSize = PageConstant.DEFAULT_PAGE_SIZE;

    @Schema(description = "分页搜索关键字", example = "")
    private String keyword;

    public void setPageIndex(Long pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            this.pageIndex = PageConstant.DEFAULT_PAGE_INDEX;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = PageConstant.DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

}
