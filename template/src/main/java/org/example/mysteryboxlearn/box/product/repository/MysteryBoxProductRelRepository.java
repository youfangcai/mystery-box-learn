package org.example.mysteryboxlearn.box.product.entity.repository;

import org.example.mysteryboxlearn.box.product.entity.MysteryBoxProductRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface MysteryBoxProductRelRepository extends JRepository<MysteryBoxProductRel, String> {
    MysteryBoxProductRelTable t = MysteryBoxProductRelTable.$;
    MysteryBoxProductRelFetcher COMPLEX_FETCHER_FOR_ADMIN = MysteryBoxProductRelFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    MysteryBoxProductRelFetcher COMPLEX_FETCHER_FOR_FRONT = MysteryBoxProductRelFetcher.$.allScalarFields()
            .creator(true);

    default Page<MysteryBoxProductRel> findPage(QueryRequest<MysteryBoxProductRelSpec> queryRequest,
                                                Fetcher<MysteryBoxProductRel> fetcher) {
        MysteryBoxProductRelSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}