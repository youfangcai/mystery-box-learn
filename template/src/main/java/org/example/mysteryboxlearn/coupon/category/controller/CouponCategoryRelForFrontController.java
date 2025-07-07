package org.example.mysteryboxlearn.coupon.category.entity.controller;

import org.example.mysteryboxlearn.coupon.category.entity.CouponCategoryRel;

@RestController
@RequestMapping("front/coupon-category-rel")
@AllArgsConstructor
@DefaultFetcherOwner(CouponCategoryRelRepository.class)
@Transactional
public class CouponCategoryRelForFrontController {
    private final CouponCategoryRelRepository couponCategoryRelRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") CouponCategoryRel findById(@PathVariable String id) {
        return couponCategoryRelRepository.findById(id, CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") CouponCategoryRel> query(@RequestBody QueryRequest<CouponCategoryRelSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return couponCategoryRelRepository.findPage(queryRequest, CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponCategoryRelInput couponCategoryRelInput) {
        if (StringUtils.hasText(couponCategoryRelInput.getId())) {
            CouponCategoryRel couponCategoryRel = couponCategoryRelRepository.findById(couponCategoryRelInput.getId(), CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!couponCategoryRel.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return couponCategoryRelRepository.save(couponCategoryRelInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponCategoryRelRepository.findByIds(ids, CouponCategoryRelRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(couponCategoryRel -> {
            if (!couponCategoryRel.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        couponCategoryRelRepository.deleteAllById(ids);
        return true;
    }
}
