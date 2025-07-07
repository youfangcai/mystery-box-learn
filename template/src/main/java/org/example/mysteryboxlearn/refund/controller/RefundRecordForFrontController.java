package org.example.mysteryboxlearn.refund.entity.controller;

import org.example.mysteryboxlearn.refund.entity.RefundRecord;

@RestController
@RequestMapping("front/refund-record")
@AllArgsConstructor
@DefaultFetcherOwner(RefundRecordRepository.class)
@Transactional
public class RefundRecordForFrontController {
    private final RefundRecordRepository refundRecordRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") RefundRecord findById(@PathVariable String id) {
        return refundRecordRepository.findById(id, RefundRecordRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") RefundRecord> query(@RequestBody QueryRequest<RefundRecordSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return refundRecordRepository.findPage(queryRequest, RefundRecordRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated RefundRecordInput refundRecordInput) {
        if (StringUtils.hasText(refundRecordInput.getId())) {
            RefundRecord refundRecord = refundRecordRepository.findById(refundRecordInput.getId(), RefundRecordRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!refundRecord.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return refundRecordRepository.save(refundRecordInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        refundRecordRepository.findByIds(ids, RefundRecordRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(refundRecord -> {
            if (!refundRecord.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        refundRecordRepository.deleteAllById(ids);
        return true;
    }
}
