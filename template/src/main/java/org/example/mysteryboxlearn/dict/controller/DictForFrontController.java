package org.example.mysteryboxlearn.dict.entity.controller;

import org.example.mysteryboxlearn.dict.entity.Dict;

@RestController
@RequestMapping("front/dict")
@AllArgsConstructor
@DefaultFetcherOwner(DictRepository.class)
@Transactional
public class DictForFrontController {
    private final DictRepository dictRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Dict findById(@PathVariable String id) {
        return dictRepository.findById(id, DictRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Dict> query(@RequestBody QueryRequest<DictSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return dictRepository.findPage(queryRequest, DictRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated DictInput dictInput) {
        if (StringUtils.hasText(dictInput.getId())) {
            Dict dict = dictRepository.findById(dictInput.getId(), DictRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!dict.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return dictRepository.save(dictInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        dictRepository.findByIds(ids, DictRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(dict -> {
            if (!dict.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        dictRepository.deleteAllById(ids);
        return true;
    }
}
