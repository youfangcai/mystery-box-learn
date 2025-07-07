package org.example.mysteryboxlearn.coupon.root.entity.repository;

import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CouponRepository extends JRepository<Coupon, String> {
    CouponTable t = CouponTable.$;
    CouponFetcher COMPLEX_FETCHER_FOR_ADMIN = CouponFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CouponFetcher COMPLEX_FETCHER_FOR_FRONT = CouponFetcher.$.allScalarFields()
            .creator(true);

    default Page<Coupon> findPage(QueryRequest<CouponSpec> queryRequest,
                                  Fetcher<Coupon> fetcher) {
        CouponSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}