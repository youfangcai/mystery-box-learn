package org.example.mysteryboxlearn.product.category.entity.controller;

import org.example.mysteryboxlearn.product.category.entity.ProductCategory;

@RestController
@RequestMapping("admin/product-category")
@AllArgsConstructor
@DefaultFetcherOwner(ProductCategoryRepository.class)
@SaCheckPermission("/product-category")
@Transactional
public class ProductCategoryForAdminController {
    private final ProductCategoryRepository productCategoryRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") ProductCategory findById(@PathVariable String id) {
        return productCategoryRepository.findById(id, ProductCategoryRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") ProductCategory> query(@RequestBody QueryRequest<ProductCategorySpec> queryRequest) {
        return productCategoryRepository.findPage(queryRequest, ProductCategoryRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated ProductCategoryInput productCategoryInput) {
        return productCategoryRepository.save(productCategoryInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        productCategoryRepository.deleteAllById(ids);
        return true;
    }
}