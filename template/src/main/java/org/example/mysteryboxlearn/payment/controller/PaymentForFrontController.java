package org.example.mysteryboxlearn.payment.entity.controller;

import org.example.mysteryboxlearn.payment.entity.Payment;

@RestController
@RequestMapping("front/payment")
@AllArgsConstructor
@DefaultFetcherOwner(PaymentRepository.class)
@Transactional
public class PaymentForFrontController {
    private final PaymentRepository paymentRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Payment findById(@PathVariable String id) {
        return paymentRepository.findById(id, PaymentRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Payment> query(@RequestBody QueryRequest<PaymentSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return paymentRepository.findPage(queryRequest, PaymentRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated PaymentInput paymentInput) {
        if (StringUtils.hasText(paymentInput.getId())) {
            Payment payment = paymentRepository.findById(paymentInput.getId(), PaymentRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!payment.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return paymentRepository.save(paymentInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        paymentRepository.findByIds(ids, PaymentRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(payment -> {
            if (!payment.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        paymentRepository.deleteAllById(ids);
        return true;
    }
}
