package org.example.mysteryboxlearn.vip.root.entity.repository;

import org.example.mysteryboxlearn.vip.root.entity.Vip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface VipRepository extends JRepository<Vip, String> {
    VipTable t = VipTable.$;
    VipFetcher COMPLEX_FETCHER_FOR_ADMIN = VipFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    VipFetcher COMPLEX_FETCHER_FOR_FRONT = VipFetcher.$.allScalarFields()
            .creator(true);

    default Page<Vip> findPage(QueryRequest<VipSpec> queryRequest,
                               Fetcher<Vip> fetcher) {
        VipSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}