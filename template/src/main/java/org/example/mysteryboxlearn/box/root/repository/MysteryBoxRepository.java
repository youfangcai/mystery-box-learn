package org.example.mysteryboxlearn.box.root.entity.repository;

import org.example.mysteryboxlearn.box.root.entity.MysteryBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface MysteryBoxRepository extends JRepository<MysteryBox, String> {
    MysteryBoxTable t = MysteryBoxTable.$;
    MysteryBoxFetcher COMPLEX_FETCHER_FOR_ADMIN = MysteryBoxFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    MysteryBoxFetcher COMPLEX_FETCHER_FOR_FRONT = MysteryBoxFetcher.$.allScalarFields()
            .creator(true);

    default Page<MysteryBox> findPage(QueryRequest<MysteryBoxSpec> queryRequest,
                                      Fetcher<MysteryBox> fetcher) {
        MysteryBoxSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}