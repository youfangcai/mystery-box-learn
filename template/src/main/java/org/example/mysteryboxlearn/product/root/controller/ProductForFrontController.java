package org.example.mysteryboxlearn.product.root.entity.controller;

import org.example.mysteryboxlearn.product.root.entity.Product;

@RestController
@RequestMapping("front/product")
@AllArgsConstructor
@DefaultFetcherOwner(ProductRepository.class)
@Transactional
public class ProductForFrontController {
    private final ProductRepository productRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Product findById(@PathVariable String id) {
        return productRepository.findById(id, ProductRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Product> query(@RequestBody QueryRequest<ProductSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return productRepository.findPage(queryRequest, ProductRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated ProductInput productInput) {
        if (StringUtils.hasText(productInput.getId())) {
            Product product = productRepository.findById(productInput.getId(), ProductRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!product.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return productRepository.save(productInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        productRepository.findByIds(ids, ProductRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(product -> {
            if (!product.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        productRepository.deleteAllById(ids);
        return true;
    }
}
