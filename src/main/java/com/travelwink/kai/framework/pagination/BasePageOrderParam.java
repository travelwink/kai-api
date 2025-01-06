package com.travelwink.kai.framework.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分页查询排序基础参数")
public class BasePageOrderParam extends BasePageParam {

    private List<OrderItem> pageSorts;

    public void defaultPageSort(OrderItem orderItem) {
        this.defaultPageSorts(Collections.singletonList(orderItem));
    }

    public void defaultPageSorts(List<OrderItem> pageSorts) {
        if (CollectionUtils.isEmpty(pageSorts)) {
            return;
        }
        this.pageSorts = pageSorts;
    }
}
