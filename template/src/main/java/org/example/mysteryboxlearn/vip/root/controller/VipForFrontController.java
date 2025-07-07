package org.example.mysteryboxlearn.vip.root.entity.controller;

import org.example.mysteryboxlearn.vip.root.entity.Vip;

@RestController
@RequestMapping("front/vip")
@AllArgsConstructor
@DefaultFetcherOwner(VipRepository.class)
@Transactional
public class VipForFrontController {
    private final VipRepository vipRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Vip findById(@PathVariable String id) {
        return vipRepository.findById(id, VipRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Vip> query(@RequestBody QueryRequest<VipSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return vipRepository.findPage(queryRequest, VipRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipInput vipInput) {
        if (StringUtils.hasText(vipInput.getId())) {
            Vip vip = vipRepository.findById(vipInput.getId(), VipRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!vip.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return vipRepository.save(vipInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipRepository.findByIds(ids, VipRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(vip -> {
            if (!vip.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        vipRepository.deleteAllById(ids);
        return true;
    }
}
