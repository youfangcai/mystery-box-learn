package org.example.mysteryboxlearn.user.wechat.entity.repository;

import org.example.mysteryboxlearn.user.wechat.entity.UserWeChat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface UserWeChatRepository extends JRepository<UserWeChat, String> {
    UserWeChatTable t = UserWeChatTable.$;
    UserWeChatFetcher COMPLEX_FETCHER_FOR_ADMIN = UserWeChatFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    UserWeChatFetcher COMPLEX_FETCHER_FOR_FRONT = UserWeChatFetcher.$.allScalarFields()
            .creator(true);

    default Page<UserWeChat> findPage(QueryRequest<UserWeChatSpec> queryRequest,
                                      Fetcher<UserWeChat> fetcher) {
        UserWeChatSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}