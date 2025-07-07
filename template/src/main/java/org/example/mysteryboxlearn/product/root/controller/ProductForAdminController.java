package org.example.mysteryboxlearn.product.root.entity.controller;

import org.example.mysteryboxlearn.product.root.entity.Product;

@RestController
@RequestMapping("admin/product")
@AllArgsConstructor
@DefaultFetcherOwner(ProductRepository.class)
@SaCheckPermission("/product")
@Transactional
public class ProductForAdminController {
    private final ProductRepository productRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Product findById(@PathVariable String id) {
        return productRepository.findById(id, ProductRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Product> query(@RequestBody QueryRequest<ProductSpec> queryRequest) {
        return productRepository.findPage(queryRequest, ProductRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated ProductInput productInput) {
        return productRepository.save(productInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        productRepository.deleteAllById(ids);
        return true;
    }
}