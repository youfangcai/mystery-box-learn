package org.example.mysteryboxlearn.refund.entity.service;

import org.example.mysteryboxlearn.refund.entity.RefundRecord;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RefundRecordService {
    private final RefundRecordRepository refundRecordRepository;

}