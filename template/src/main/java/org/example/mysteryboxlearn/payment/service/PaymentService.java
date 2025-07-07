package org.example.mysteryboxlearn.payment.entity.service;

import org.example.mysteryboxlearn.payment.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;

}