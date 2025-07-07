package org.example.mysteryboxlearn.coupon.category.entity.controller;

import org.example.mysteryboxlearn.coupon.category.entity.CouponCategoryRel;

@RestController
@RequestMapping("admin/coupon-category-rel")
@AllArgsConstructor
@DefaultFetcherOwner(CouponCategoryRelRepository.class)
@SaCheckPermission("/coupon-category-rel")
@Transactional
public class CouponCategoryRelForAdminController {
    private final CouponCategoryRelRepository couponCategoryRelRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponCategoryRel findById(@PathVariable String id) {
        return couponCategoryRelRepository.findById(id, CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponCategoryRel> query(@RequestBody QueryRequest<CouponCategoryRelSpec> queryRequest) {
        return couponCategoryRelRepository.findPage(queryRequest, CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponCategoryRelInput couponCategoryRelInput) {
        return couponCategoryRelRepository.save(couponCategoryRelInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponCategoryRelRepository.deleteAllById(ids);
        return true;
    }
}