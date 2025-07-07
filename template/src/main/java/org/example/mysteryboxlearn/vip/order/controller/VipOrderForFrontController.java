package org.example.mysteryboxlearn.vip.order.entity.controller;

import org.example.mysteryboxlearn.vip.order.entity.VipOrder;

@RestController
@RequestMapping("front/vip-order")
@AllArgsConstructor
@DefaultFetcherOwner(VipOrderRepository.class)
@Transactional
public class VipOrderForFrontController {
    private final VipOrderRepository vipOrderRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") VipOrder findById(@PathVariable String id) {
        return vipOrderRepository.findById(id, VipOrderRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") VipOrder> query(@RequestBody QueryRequest<VipOrderSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return vipOrderRepository.findPage(queryRequest, VipOrderRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipOrderInput vipOrderInput) {
        if (StringUtils.hasText(vipOrderInput.getId())) {
            VipOrder vipOrder = vipOrderRepository.findById(vipOrderInput.getId(), VipOrderRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!vipOrder.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return vipOrderRepository.save(vipOrderInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipOrderRepository.findByIds(ids, VipOrderRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(vipOrder -> {
            if (!vipOrder.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        vipOrderRepository.deleteAllById(ids);
        return true;
    }
}
