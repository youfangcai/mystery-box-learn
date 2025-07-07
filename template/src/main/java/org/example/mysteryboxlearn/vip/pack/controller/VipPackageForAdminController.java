package org.example.mysteryboxlearn.vip.pack.entity.controller;

import org.example.mysteryboxlearn.vip.pack.entity.VipPackage;

@RestController
@RequestMapping("admin/vip-package")
@AllArgsConstructor
@DefaultFetcherOwner(VipPackageRepository.class)
@SaCheckPermission("/vip-package")
@Transactional
public class VipPackageForAdminController {
    private final VipPackageRepository vipPackageRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") VipPackage findById(@PathVariable String id) {
        return vipPackageRepository.findById(id, VipPackageRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") VipPackage> query(@RequestBody QueryRequest<VipPackageSpec> queryRequest) {
        return vipPackageRepository.findPage(queryRequest, VipPackageRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated VipPackageInput vipPackageInput) {
        return vipPackageRepository.save(vipPackageInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        vipPackageRepository.deleteAllById(ids);
        return true;
    }
}