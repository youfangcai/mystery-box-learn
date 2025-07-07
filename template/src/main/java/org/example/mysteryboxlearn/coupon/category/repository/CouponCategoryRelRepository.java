package org.example.mysteryboxlearn.coupon.category.entity.repository;

import org.example.mysteryboxlearn.coupon.category.entity.CouponCategoryRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CouponCategoryRelRepository extends JRepository<CouponCategoryRel, String> {
    CouponCategoryRelTable t = CouponCategoryRelTable.$;
    CouponCategoryRelFetcher COMPLEX_FETCHER_FOR_ADMIN = CouponCategoryRelFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CouponCategoryRelFetcher COMPLEX_FETCHER_FOR_FRONT = CouponCategoryRelFetcher.$.allScalarFields()
            .creator(true);

    default Page<CouponCategoryRel> findPage(QueryRequest<CouponCategoryRelSpec> queryRequest,
                                             Fetcher<CouponCategoryRel> fetcher) {
        CouponCategoryRelSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}