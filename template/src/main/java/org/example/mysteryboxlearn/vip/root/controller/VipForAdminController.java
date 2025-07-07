package org.example.mysteryboxlearn.vip.root.entity.controller;

import org.example.mysteryboxlearn.vip.root.entity.Vip;

@RestController
@RequestMapping("admin/vip")
@AllArgsConstructor
@DefaultFetcherOwner(VipRepository.class)
@SaCheckPermission("/vip")
@Transactional
public class VipForAdminController {
    private final VipRepository vipRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Vip findById(@PathVariable String id) {
        return vipRepository.findById(id, VipRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Vip> query(@RequestBody QueryRequest<VipSpec> queryRequest) {
        return vipRepository.findPage(queryRequest, VipRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipInput vipInput) {
        return vipRepository.save(vipInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipRepository.deleteAllById(ids);
        return true;
    }
}