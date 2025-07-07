package org.example.mysteryboxlearn.coupon.user.entity.repository;

import org.example.mysteryboxlearn.coupon.user.entity.CouponUserRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CouponUserRelRepository extends JRepository<CouponUserRel, String> {
    CouponUserRelTable t = CouponUserRelTable.$;
    CouponUserRelFetcher COMPLEX_FETCHER_FOR_ADMIN = CouponUserRelFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CouponUserRelFetcher COMPLEX_FETCHER_FOR_FRONT = CouponUserRelFetcher.$.allScalarFields()
            .creator(true);

    default Page<CouponUserRel> findPage(QueryRequest<CouponUserRelSpec> queryRequest,
                                         Fetcher<CouponUserRel> fetcher) {
        CouponUserRelSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}