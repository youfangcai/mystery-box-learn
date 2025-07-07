package org.example.mysteryboxlearn.coupon.box.entity.repository;

import org.example.mysteryboxlearn.coupon.box.entity.CouponBoxRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CouponBoxRelRepository extends JRepository<CouponBoxRel, String> {
    CouponBoxRelTable t = CouponBoxRelTable.$;
    CouponBoxRelFetcher COMPLEX_FETCHER_FOR_ADMIN = CouponBoxRelFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CouponBoxRelFetcher COMPLEX_FETCHER_FOR_FRONT = CouponBoxRelFetcher.$.allScalarFields()
            .creator(true);

    default Page<CouponBoxRel> findPage(QueryRequest<CouponBoxRelSpec> queryRequest,
                                        Fetcher<CouponBoxRel> fetcher) {
        CouponBoxRelSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}