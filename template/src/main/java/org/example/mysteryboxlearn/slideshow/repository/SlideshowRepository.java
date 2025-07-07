package org.example.mysteryboxlearn.slideshow.entity.repository;

import org.example.mysteryboxlearn.slideshow.entity.Slideshow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface SlideshowRepository extends JRepository<Slideshow, String> {
    SlideshowTable t = SlideshowTable.$;
    SlideshowFetcher COMPLEX_FETCHER_FOR_ADMIN = SlideshowFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    SlideshowFetcher COMPLEX_FETCHER_FOR_FRONT = SlideshowFetcher.$.allScalarFields()
            .creator(true);

    default Page<Slideshow> findPage(QueryRequest<SlideshowSpec> queryRequest,
                                     Fetcher<Slideshow> fetcher) {
        SlideshowSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}