package org.example.mysteryboxlearn.product.service.adapter;

import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.example.mysteryboxlearn.product.domain.vo.ProductInfoReq;
import org.example.mysteryboxlearn.product.domain.vo.ProductInfoResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductAdapter {
    public static ProductAdapter INSTANCE = Mappers.getMapper(ProductAdapter.class);
    // 各层数据对象间的转换方法
    public ProductInfoResp convertToResp(Product product);
    public Product convertToProduct(ProductInfoResp resp);
    public Product convertToProduct(ProductInfoReq req);
}