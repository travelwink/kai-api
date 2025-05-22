package com.travelwink.kai.framework.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PageModel<T> extends Page<T> {
    private BasePageParam pageParam;

    private OrderItem defaultOrderItem;

    public PageModel(BasePageParam pageParam) {
        super(pageParam.getPageIndex(), pageParam.getPageSize());
    }

}
