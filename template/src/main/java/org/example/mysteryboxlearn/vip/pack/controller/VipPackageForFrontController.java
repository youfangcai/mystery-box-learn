package org.example.mysteryboxlearn.vip.pack.entity.controller;

import org.example.mysteryboxlearn.vip.pack.entity.VipPackage;

@RestController
@RequestMapping("front/vip-package")
@AllArgsConstructor
@DefaultFetcherOwner(VipPackageRepository.class)
@Transactional
public class VipPackageForFrontController {
    private final VipPackageRepository vipPackageRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") VipPackage findById(@PathVariable String id) {
        return vipPackageRepository.findById(id, VipPackageRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") VipPackage> query(@RequestBody QueryRequest<VipPackageSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return vipPackageRepository.findPage(queryRequest, VipPackageRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipPackageInput vipPackageInput) {
        if (StringUtils.hasText(vipPackageInput.getId())) {
            VipPackage vipPackage = vipPackageRepository.findById(vipPackageInput.getId(), VipPackageRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!vipPackage.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return vipPackageRepository.save(vipPackageInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipPackageRepository.findByIds(ids, VipPackageRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(vipPackage -> {
            if (!vipPackage.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        vipPackageRepository.deleteAllById(ids);
        return true;
    }
}
