package org.example.mysteryboxlearn.feedback.entity.repository;

import org.example.mysteryboxlearn.feedback.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface FeedbackRepository extends JRepository<Feedback, String> {
    FeedbackTable t = FeedbackTable.$;
    FeedbackFetcher COMPLEX_FETCHER_FOR_ADMIN = FeedbackFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    FeedbackFetcher COMPLEX_FETCHER_FOR_FRONT = FeedbackFetcher.$.allScalarFields()
            .creator(true);

    default Page<Feedback> findPage(QueryRequest<FeedbackSpec> queryRequest,
                                    Fetcher<Feedback> fetcher) {
        FeedbackSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}