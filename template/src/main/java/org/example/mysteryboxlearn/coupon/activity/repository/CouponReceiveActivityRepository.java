package org.example.mysteryboxlearn.coupon.activity.entity.repository;

import org.example.mysteryboxlearn.coupon.activity.entity.CouponReceiveActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CouponReceiveActivityRepository extends JRepository<CouponReceiveActivity, String> {
    CouponReceiveActivityTable t = CouponReceiveActivityTable.$;
    CouponReceiveActivityFetcher COMPLEX_FETCHER_FOR_ADMIN = CouponReceiveActivityFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CouponReceiveActivityFetcher COMPLEX_FETCHER_FOR_FRONT = CouponReceiveActivityFetcher.$.allScalarFields()
            .creator(true);

    default Page<CouponReceiveActivity> findPage(QueryRequest<CouponReceiveActivitySpec> queryRequest,
                                                 Fetcher<CouponReceiveActivity> fetcher) {
        CouponReceiveActivitySpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}