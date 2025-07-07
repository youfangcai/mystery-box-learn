package org.example.mysteryboxlearn.vip.order.entity.repository;

import org.example.mysteryboxlearn.vip.order.entity.VipOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface VipOrderRepository extends JRepository<VipOrder, String> {
    VipOrderTable t = VipOrderTable.$;
    VipOrderFetcher COMPLEX_FETCHER_FOR_ADMIN = VipOrderFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    VipOrderFetcher COMPLEX_FETCHER_FOR_FRONT = VipOrderFetcher.$.allScalarFields()
            .creator(true);

    default Page<VipOrder> findPage(QueryRequest<VipOrderSpec> queryRequest,
                                    Fetcher<VipOrder> fetcher) {
        VipOrderSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}