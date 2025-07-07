package org.example.mysteryboxlearn.carriage.entity.repository;

import org.example.mysteryboxlearn.carriage.entity.CarriageTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface CarriageTemplateRepository extends JRepository<CarriageTemplate, String> {
    CarriageTemplateTable t = CarriageTemplateTable.$;
    CarriageTemplateFetcher COMPLEX_FETCHER_FOR_ADMIN = CarriageTemplateFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    CarriageTemplateFetcher COMPLEX_FETCHER_FOR_FRONT = CarriageTemplateFetcher.$.allScalarFields()
            .creator(true);

    default Page<CarriageTemplate> findPage(QueryRequest<CarriageTemplateSpec> queryRequest,
                                            Fetcher<CarriageTemplate> fetcher) {
        CarriageTemplateSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}