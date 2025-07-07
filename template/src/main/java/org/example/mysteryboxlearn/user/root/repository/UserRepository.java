package org.example.mysteryboxlearn.user.root.entity.repository;

import org.example.mysteryboxlearn.user.root.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface UserRepository extends JRepository<User, String> {
    UserTable t = UserTable.$;
    UserFetcher COMPLEX_FETCHER_FOR_ADMIN = UserFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    UserFetcher COMPLEX_FETCHER_FOR_FRONT = UserFetcher.$.allScalarFields()
            .creator(true);

    default Page<User> findPage(QueryRequest<UserSpec> queryRequest,
                                Fetcher<User> fetcher) {
        UserSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}