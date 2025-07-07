package org.example.mysteryboxlearn.product.category.entity.controller;

import org.example.mysteryboxlearn.product.category.entity.ProductCategory;

@RestController
@RequestMapping("front/product-category")
@AllArgsConstructor
@DefaultFetcherOwner(ProductCategoryRepository.class)
@Transactional
public class ProductCategoryForFrontController {
    private final ProductCategoryRepository productCategoryRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") ProductCategory findById(@PathVariable String id) {
        return productCategoryRepository.findById(id, ProductCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") ProductCategory> query(@RequestBody QueryRequest<ProductCategorySpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return productCategoryRepository.findPage(queryRequest, ProductCategoryRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated ProductCategoryInput productCategoryInput) {
        if (StringUtils.hasText(productCategoryInput.getId())) {
            ProductCategory productCategory = productCategoryRepository.findById(productCategoryInput.getId(), ProductCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!productCategory.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return productCategoryRepository.save(productCategoryInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        productCategoryRepository.findByIds(ids, ProductCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(productCategory -> {
            if (!productCategory.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        productCategoryRepository.deleteAllById(ids);
        return true;
    }
}
