package org.example.mysteryboxlearn.coupon.root.entity.controller;

import org.example.mysteryboxlearn.coupon.root.entity.Coupon;

@RestController
@RequestMapping("admin/coupon")
@AllArgsConstructor
@DefaultFetcherOwner(CouponRepository.class)
@SaCheckPermission("/coupon")
@Transactional
public class CouponForAdminController {
    private final CouponRepository couponRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Coupon findById(@PathVariable String id) {
        return couponRepository.findById(id, CouponRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Coupon> query(@RequestBody QueryRequest<CouponSpec> queryRequest) {
        return couponRepository.findPage(queryRequest, CouponRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponInput couponInput) {
        return couponRepository.save(couponInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponRepository.deleteAllById(ids);
        return true;
    }
}