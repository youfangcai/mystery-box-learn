package org.example.mysteryboxlearn.product.root.entity.service;

import org.example.mysteryboxlearn.product.root.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

}