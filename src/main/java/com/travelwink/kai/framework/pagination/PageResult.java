package com.travelwink.kai.framework.pagination;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@Schema(description = "分页查询结果")
public class PageResult<T> implements Serializable {

    @Schema(description = "总条数")
    private long total = 0;

    @Schema(description = "数据列表")
    private List<T> records = Collections.emptyList();

    @Schema(description = "当前页码")
    private long pageIndex;

    @Schema(description = "当前页条数")
    private long pageSize;

    public PageResult(IPage<T> page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
    }
}
