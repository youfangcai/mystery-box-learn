package org.example.mysteryboxlearn.payment.entity.controller;

import org.example.mysteryboxlearn.payment.entity.Payment;

@RestController
@RequestMapping("admin/payment")
@AllArgsConstructor
@DefaultFetcherOwner(PaymentRepository.class)
@SaCheckPermission("/payment")
@Transactional
public class PaymentForAdminController {
    private final PaymentRepository paymentRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Payment findById(@PathVariable String id) {
        return paymentRepository.findById(id, PaymentRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Payment> query(@RequestBody QueryRequest<PaymentSpec> queryRequest) {
        return paymentRepository.findPage(queryRequest, PaymentRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated PaymentInput paymentInput) {
        return paymentRepository.save(paymentInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        paymentRepository.deleteAllById(ids);
        return true;
    }
}