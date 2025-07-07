package org.example.mysteryboxlearn.vip.order.entity.controller;

import org.example.mysteryboxlearn.vip.order.entity.VipOrder;

@RestController
@RequestMapping("admin/vip-order")
@AllArgsConstructor
@DefaultFetcherOwner(VipOrderRepository.class)
@SaCheckPermission("/vip-order")
@Transactional
public class VipOrderForAdminController {
    private final VipOrderRepository vipOrderRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") VipOrder findById(@PathVariable String id) {
        return vipOrderRepository.findById(id, VipOrderRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") VipOrder> query(@RequestBody QueryRequest<VipOrderSpec> queryRequest) {
        return vipOrderRepository.findPage(queryRequest, VipOrderRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipOrderInput vipOrderInput) {
        return vipOrderRepository.save(vipOrderInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipOrderRepository.deleteAllById(ids);
        return true;
    }
}