package org.example.mysteryboxlearn.coupon.user.entity.service;

import org.example.mysteryboxlearn.coupon.user.entity.CouponUserRel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CouponUserRelService {
    private final CouponUserRelRepository couponUserRelRepository;

}