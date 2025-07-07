package org.example.mysteryboxlearn.coupon.root.entity.service;

import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CouponService {
    private final CouponRepository couponRepository;

}