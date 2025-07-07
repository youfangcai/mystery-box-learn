package org.example.mysteryboxlearn.vip.pack.entity.repository;

import org.example.mysteryboxlearn.vip.pack.entity.VipPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface VipPackageRepository extends JRepository<VipPackage, String> {
    VipPackageTable t = VipPackageTable.$;
    VipPackageFetcher COMPLEX_FETCHER_FOR_ADMIN = VipPackageFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    VipPackageFetcher COMPLEX_FETCHER_FOR_FRONT = VipPackageFetcher.$.allScalarFields()
            .creator(true);

    default Page<VipPackage> findPage(QueryRequest<VipPackageSpec> queryRequest,
                                      Fetcher<VipPackage> fetcher) {
        VipPackageSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}