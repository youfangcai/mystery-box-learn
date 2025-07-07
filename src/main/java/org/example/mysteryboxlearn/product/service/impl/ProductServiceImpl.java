package org.example.mysteryboxlearn.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mysteryboxlearn.product.mapper.ProductMapper;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.example.mysteryboxlearn.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
