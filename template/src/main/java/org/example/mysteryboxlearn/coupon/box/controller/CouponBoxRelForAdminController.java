package org.example.mysteryboxlearn.coupon.box.entity.controller;

import org.example.mysteryboxlearn.coupon.box.entity.CouponBoxRel;

@RestController
@RequestMapping("admin/coupon-box-rel")
@AllArgsConstructor
@DefaultFetcherOwner(CouponBoxRelRepository.class)
@SaCheckPermission("/coupon-box-rel")
@Transactional
public class CouponBoxRelForAdminController {
    private final CouponBoxRelRepository couponBoxRelRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponBoxRel findById(@PathVariable String id) {
        return couponBoxRelRepository.findById(id, CouponBoxRelRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponBoxRel> query(@RequestBody QueryRequest<CouponBoxRelSpec> queryRequest) {
        return couponBoxRelRepository.findPage(queryRequest, CouponBoxRelRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponBoxRelInput couponBoxRelInput) {
        return couponBoxRelRepository.save(couponBoxRelInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponBoxRelRepository.deleteAllById(ids);
        return true;
    }
}