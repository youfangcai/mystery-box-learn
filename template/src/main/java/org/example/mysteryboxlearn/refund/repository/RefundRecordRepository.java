package org.example.mysteryboxlearn.refund.entity.repository;

import org.example.mysteryboxlearn.refund.entity.RefundRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface RefundRecordRepository extends JRepository<RefundRecord, String> {
    RefundRecordTable t = RefundRecordTable.$;
    RefundRecordFetcher COMPLEX_FETCHER_FOR_ADMIN = RefundRecordFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    RefundRecordFetcher COMPLEX_FETCHER_FOR_FRONT = RefundRecordFetcher.$.allScalarFields()
            .creator(true);

    default Page<RefundRecord> findPage(QueryRequest<RefundRecordSpec> queryRequest,
                                        Fetcher<RefundRecord> fetcher) {
        RefundRecordSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}