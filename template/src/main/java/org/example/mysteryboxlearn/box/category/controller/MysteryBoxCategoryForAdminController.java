package org.example.mysteryboxlearn.box.category.entity.controller;

import org.example.mysteryboxlearn.box.category.entity.MysteryBoxCategory;

@RestController
@RequestMapping("admin/mystery-box-category")
@AllArgsConstructor
@DefaultFetcherOwner(MysteryBoxCategoryRepository.class)
@SaCheckPermission("/mystery-box-category")
@Transactional
public class MysteryBoxCategoryForAdminController {
    private final MysteryBoxCategoryRepository mysteryBoxCategoryRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBoxCategory findById(@PathVariable String id) {
        return mysteryBoxCategoryRepository.findById(id, MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBoxCategory> query(@RequestBody QueryRequest<MysteryBoxCategorySpec> queryRequest) {
        return mysteryBoxCategoryRepository.findPage(queryRequest, MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MysteryBoxCategoryInput mysteryBoxCategoryInput) {
        return mysteryBoxCategoryRepository.save(mysteryBoxCategoryInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        mysteryBoxCategoryRepository.deleteAllById(ids);
        return true;
    }
}