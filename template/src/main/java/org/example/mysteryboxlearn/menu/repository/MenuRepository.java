package org.example.mysteryboxlearn.menu.entity.repository;

import org.example.mysteryboxlearn.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface MenuRepository extends JRepository<Menu, String> {
    MenuTable t = MenuTable.$;
    MenuFetcher COMPLEX_FETCHER_FOR_ADMIN = MenuFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    MenuFetcher COMPLEX_FETCHER_FOR_FRONT = MenuFetcher.$.allScalarFields()
            .creator(true);

    default Page<Menu> findPage(QueryRequest<MenuSpec> queryRequest,
                                Fetcher<Menu> fetcher) {
        MenuSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}