package org.example.mysteryboxlearn.box.category.entity.repository;

import org.example.mysteryboxlearn.box.category.entity.MysteryBoxCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface MysteryBoxCategoryRepository extends JRepository<MysteryBoxCategory, String> {
    MysteryBoxCategoryTable t = MysteryBoxCategoryTable.$;
    MysteryBoxCategoryFetcher COMPLEX_FETCHER_FOR_ADMIN = MysteryBoxCategoryFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    MysteryBoxCategoryFetcher COMPLEX_FETCHER_FOR_FRONT = MysteryBoxCategoryFetcher.$.allScalarFields()
            .creator(true);

    default Page<MysteryBoxCategory> findPage(QueryRequest<MysteryBoxCategorySpec> queryRequest,
                                              Fetcher<MysteryBoxCategory> fetcher) {
        MysteryBoxCategorySpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}