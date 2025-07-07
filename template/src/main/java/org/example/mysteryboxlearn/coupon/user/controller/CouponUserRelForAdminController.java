package org.example.mysteryboxlearn.coupon.user.entity.controller;

import org.example.mysteryboxlearn.coupon.user.entity.CouponUserRel;

@RestController
@RequestMapping("admin/coupon-user-rel")
@AllArgsConstructor
@DefaultFetcherOwner(CouponUserRelRepository.class)
@SaCheckPermission("/coupon-user-rel")
@Transactional
public class CouponUserRelForAdminController {
    private final CouponUserRelRepository couponUserRelRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponUserRel findById(@PathVariable String id) {
        return couponUserRelRepository.findById(id, CouponUserRelRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponUserRel> query(@RequestBody QueryRequest<CouponUserRelSpec> queryRequest) {
        return couponUserRelRepository.findPage(queryRequest, CouponUserRelRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponUserRelInput couponUserRelInput) {
        return couponUserRelRepository.save(couponUserRelInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponUserRelRepository.deleteAllById(ids);
        return true;
    }
}