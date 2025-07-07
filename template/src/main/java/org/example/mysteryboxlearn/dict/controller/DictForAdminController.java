package org.example.mysteryboxlearn.dict.entity.controller;

import org.example.mysteryboxlearn.dict.entity.Dict;

@RestController
@RequestMapping("admin/dict")
@AllArgsConstructor
@DefaultFetcherOwner(DictRepository.class)
@SaCheckPermission("/dict")
@Transactional
public class DictForAdminController {
    private final DictRepository dictRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Dict findById(@PathVariable String id) {
        return dictRepository.findById(id, DictRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Dict> query(@RequestBody QueryRequest<DictSpec> queryRequest) {
        return dictRepository.findPage(queryRequest, DictRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated DictInput dictInput) {
        return dictRepository.save(dictInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        dictRepository.deleteAllById(ids);
        return true;
    }
}