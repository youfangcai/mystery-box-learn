package org.example.mysteryboxlearn.box.root.entity.controller;

import org.example.mysteryboxlearn.box.root.entity.MysteryBox;

@RestController
@RequestMapping("admin/mystery-box")
@AllArgsConstructor
@DefaultFetcherOwner(MysteryBoxRepository.class)
@SaCheckPermission("/mystery-box")
@Transactional
public class MysteryBoxForAdminController {
    private final MysteryBoxRepository mysteryBoxRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBox findById(@PathVariable String id) {
        return mysteryBoxRepository.findById(id, MysteryBoxRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") MysteryBox> query(@RequestBody QueryRequest<MysteryBoxSpec> queryRequest) {
        return mysteryBoxRepository.findPage(queryRequest, MysteryBoxRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MysteryBoxInput mysteryBoxInput) {
        return mysteryBoxRepository.save(mysteryBoxInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        mysteryBoxRepository.deleteAllById(ids);
        return true;
    }
}