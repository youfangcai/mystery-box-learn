package org.example.mysteryboxlearn.dict.entity.repository;

import org.example.mysteryboxlearn.dict.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface DictRepository extends JRepository<Dict, String> {
    DictTable t = DictTable.$;
    DictFetcher COMPLEX_FETCHER_FOR_ADMIN = DictFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    DictFetcher COMPLEX_FETCHER_FOR_FRONT = DictFetcher.$.allScalarFields()
            .creator(true);

    default Page<Dict> findPage(QueryRequest<DictSpec> queryRequest,
                                Fetcher<Dict> fetcher) {
        DictSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}