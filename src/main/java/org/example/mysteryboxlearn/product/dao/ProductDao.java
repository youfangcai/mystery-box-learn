package org.example.mysteryboxlearn.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.example.mysteryboxlearn.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static javax.management.Query.in;

@Service
public class ProductDao extends ServiceImpl<ProductMapper, Product> {
    // 通过多个productId批量查找
    public List<Product> getProducts(List<String> productIds){
        return lambdaQuery()
                .in(Product::getId, productIds)
                .list();
    }
}
