package org.example.mysteryboxlearn.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mysteryboxlearn.product.domain.entity.ProductCategory;
import org.example.mysteryboxlearn.product.mapper.ProductCategoryMapper;
import org.example.mysteryboxlearn.product.service.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
}
