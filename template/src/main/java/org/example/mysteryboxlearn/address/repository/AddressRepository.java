package org.example.mysteryboxlearn.address.entity.repository;

import org.example.mysteryboxlearn.address.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface AddressRepository extends JRepository<Address, String> {
    AddressTable t = AddressTable.$;
    AddressFetcher COMPLEX_FETCHER_FOR_ADMIN = AddressFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    AddressFetcher COMPLEX_FETCHER_FOR_FRONT = AddressFetcher.$.allScalarFields()
            .creator(true);

    default Page<Address> findPage(QueryRequest<AddressSpec> queryRequest,
                                   Fetcher<Address> fetcher) {
        AddressSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}