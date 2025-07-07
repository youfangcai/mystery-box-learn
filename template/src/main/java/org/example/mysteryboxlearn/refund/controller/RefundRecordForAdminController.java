package org.example.mysteryboxlearn.refund.entity.controller;

import org.example.mysteryboxlearn.refund.entity.RefundRecord;

@RestController
@RequestMapping("admin/refund-record")
@AllArgsConstructor
@DefaultFetcherOwner(RefundRecordRepository.class)
@SaCheckPermission("/refund-record")
@Transactional
public class RefundRecordForAdminController {
    private final RefundRecordRepository refundRecordRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") RefundRecord findById(@PathVariable String id) {
        return refundRecordRepository.findById(id, RefundRecordRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") RefundRecord> query(@RequestBody QueryRequest<RefundRecordSpec> queryRequest) {
        return refundRecordRepository.findPage(queryRequest, RefundRecordRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated RefundRecordInput refundRecordInput) {
        return refundRecordRepository.save(refundRecordInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        refundRecordRepository.deleteAllById(ids);
        return true;
    }
}