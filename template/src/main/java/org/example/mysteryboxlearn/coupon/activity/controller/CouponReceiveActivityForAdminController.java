package org.example.mysteryboxlearn.coupon.activity.entity.controller;

import org.example.mysteryboxlearn.coupon.activity.entity.CouponReceiveActivity;

@RestController
@RequestMapping("admin/coupon-receive-activity")
@AllArgsConstructor
@DefaultFetcherOwner(CouponReceiveActivityRepository.class)
@SaCheckPermission("/coupon-receive-activity")
@Transactional
public class CouponReceiveActivityForAdminController {
    private final CouponReceiveActivityRepository couponReceiveActivityRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponReceiveActivity findById(@PathVariable String id) {
        return couponReceiveActivityRepository.findById(id, CouponReceiveActivityRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CouponReceiveActivity> query(@RequestBody QueryRequest<CouponReceiveActivitySpec> queryRequest) {
        return couponReceiveActivityRepository.findPage(queryRequest, CouponReceiveActivityRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CouponReceiveActivityInput couponReceiveActivityInput) {
        return couponReceiveActivityRepository.save(couponReceiveActivityInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        couponReceiveActivityRepository.deleteAllById(ids);
        return true;
    }
}