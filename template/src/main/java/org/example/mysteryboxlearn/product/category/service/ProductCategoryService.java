package org.example.mysteryboxlearn.product.category.entity.service;

import org.example.mysteryboxlearn.product.category.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

}