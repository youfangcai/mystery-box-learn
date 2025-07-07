package org.example.mysteryboxlearn.payment.entity.repository;

import org.example.mysteryboxlearn.payment.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface PaymentRepository extends JRepository<Payment, String> {
    PaymentTable t = PaymentTable.$;
    PaymentFetcher COMPLEX_FETCHER_FOR_ADMIN = PaymentFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    PaymentFetcher COMPLEX_FETCHER_FOR_FRONT = PaymentFetcher.$.allScalarFields()
            .creator(true);

    default Page<Payment> findPage(QueryRequest<PaymentSpec> queryRequest,
                                   Fetcher<Payment> fetcher) {
        PaymentSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}