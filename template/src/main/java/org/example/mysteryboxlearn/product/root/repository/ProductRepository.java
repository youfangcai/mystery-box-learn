package org.example.mysteryboxlearn.product.root.entity.repository;

import org.example.mysteryboxlearn.product.root.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public interface ProductRepository extends JRepository<Product, String> {
    ProductTable t = ProductTable.$;
    ProductFetcher COMPLEX_FETCHER_FOR_ADMIN = ProductFetcher.$.allScalarFields()
            .creator(UserFetcher.$.phone().nickname())
            .editor(UserFetcher.$.phone().nickname());
    ProductFetcher COMPLEX_FETCHER_FOR_FRONT = ProductFetcher.$.allScalarFields()
            .creator(true);

    default Page<Product> findPage(QueryRequest<ProductSpec> queryRequest,
                                   Fetcher<Product> fetcher) {
        ProductSpec query = queryRequest.getQuery();
        Pageable pageable = queryRequest.toPageable();
        return sql().createQuery(t)
                .where(query)
                .orderBy(SpringOrders.toOrders(t, pageable.getSort()))
                .select(t.fetch(fetcher))
                .fetchPage(queryRequest.getPageNum() - 1, queryRequest.getPageSize(),
                        SpringPageFactory.getInstance());
    }
}