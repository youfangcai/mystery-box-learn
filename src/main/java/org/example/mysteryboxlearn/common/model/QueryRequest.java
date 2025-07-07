package org.example.mysteryboxlearn.common.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
public class QueryRequest<T> {

    private T query;

    private Integer pageSize = 10;

    private Integer pageNum = 1;

//    private LikMode likeMode;

    private List<QuerySort> sorts;

    public Pageable toPageable() {
        if (!CollectionUtils.isEmpty(sorts)) {
            return PageRequest.of(getPageNum() - 1, getPageSize(),
                    Sort.by(sorts.stream()
                            .map(QuerySort::toSort)
                            .collect(Collectors.toList()))
            );
        }
        return PageRequest.of(getPageNum() - 1, getPageSize(),
                Sort.by(Sort.Order.desc("createdTime")));
    }

    public Pageable toPageable(Sort.Order... orders) {
        return PageRequest.of(getPageNum() - 1, getPageSize(),
                Sort.by(orders));
    }

    public static class QuerySort {
        private String property;
        private Sort.Direction direction;

        public Sort.Order toSort() {
            return new Sort.Order(direction, property);
        }
    }
}
