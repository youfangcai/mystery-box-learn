package org.example.mysteryboxlearn.coupon.category.entity.service;

import org.example.mysteryboxlearn.coupon.category.entity.CouponCategoryRel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CouponCategoryRelService {
    private final CouponCategoryRelRepository couponCategoryRelRepository;

}