package org.example.mysteryboxlearn.box.category.entity.controller;

import org.example.mysteryboxlearn.box.category.entity.MysteryBoxCategory;

@RestController
@RequestMapping("front/mystery-box-category")
@AllArgsConstructor
@DefaultFetcherOwner(MysteryBoxCategoryRepository.class)
@Transactional
public class MysteryBoxCategoryForFrontController {
    private final MysteryBoxCategoryRepository mysteryBoxCategoryRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") MysteryBoxCategory findById(@PathVariable String id) {
        return mysteryBoxCategoryRepository.findById(id, MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") MysteryBoxCategory> query(@RequestBody QueryRequest<MysteryBoxCategorySpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return mysteryBoxCategoryRepository.findPage(queryRequest, MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MysteryBoxCategoryInput mysteryBoxCategoryInput) {
        if (StringUtils.hasText(mysteryBoxCategoryInput.getId())) {
            MysteryBoxCategory mysteryBoxCategory = mysteryBoxCategoryRepository.findById(mysteryBoxCategoryInput.getId(), MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!mysteryBoxCategory.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return mysteryBoxCategoryRepository.save(mysteryBoxCategoryInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        mysteryBoxCategoryRepository.findByIds(ids, MysteryBoxCategoryRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(mysteryBoxCategory -> {
            if (!mysteryBoxCategory.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        mysteryBoxCategoryRepository.deleteAllById(ids);
        return true;
    }
}
