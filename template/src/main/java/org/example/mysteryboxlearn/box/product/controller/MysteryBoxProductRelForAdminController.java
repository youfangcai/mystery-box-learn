package org.example.mysteryboxlearn.box.product.entity.controller;

import org.example.mysteryboxlearn.box.product.entity.MysteryBoxProductRel;

@RestController
@RequestMapping("admin/mystery-box-product-rel")
@AllArgsConstructor
@DefaultFetcherOwner(MysteryBoxProductRelRepository.class)
@SaCheckPermission("/mystery-box-product-rel")
@Transactional
public class MysteryBoxProductRelForAdminController {
    private final MysteryBoxProductRelRepository mysteryBoxProductRelRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBoxProductRel findById(@PathVariable String id) {
        return mysteryBoxProductRelRepository.findById(id, MysteryBoxProductRelRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBoxProductRel> query(@RequestBody QueryRequest<MysteryBoxProductRelSpec> queryRequest) {
        return mysteryBoxProductRelRepository.findPage(queryRequest, MysteryBoxProductRelRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MysteryBoxProductRelInput mysteryBoxProductRelInput) {
        return mysteryBoxProductRelRepository.save(mysteryBoxProductRelInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        mysteryBoxProductRelRepository.deleteAllById(ids);
        return true;
    }
}