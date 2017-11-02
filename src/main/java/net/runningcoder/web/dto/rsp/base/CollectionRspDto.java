package net.runningcoder.web.dto.rsp.base;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class CollectionRspDto<T> {
    private long total;
    private List<T> data = Lists.newArrayList();
}
