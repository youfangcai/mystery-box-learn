package org.example.mysteryboxlearn.box.root.entity.controller;

import org.example.mysteryboxlearn.box.root.entity.MysteryBox;

@RestController
@RequestMapping("front/mystery-box")
@AllArgsConstructor
@DefaultFetcherOwner(MysteryBoxRepository.class)
@Transactional
public class MysteryBoxForFrontController {
    private final MysteryBoxRepository mysteryBoxRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") MysteryBox findById(@PathVariable String id) {
        return mysteryBoxRepository.findById(id, MysteryBoxRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") MysteryBox> query(@RequestBody QueryRequest<MysteryBoxSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return mysteryBoxRepository.findPage(queryRequest, MysteryBoxRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MysteryBoxInput mysteryBoxInput) {
        if (StringUtils.hasText(mysteryBoxInput.getId())) {
            MysteryBox mysteryBox = mysteryBoxRepository.findById(mysteryBoxInput.getId(), MysteryBoxRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!mysteryBox.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return mysteryBoxRepository.save(mysteryBoxInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        mysteryBoxRepository.findByIds(ids, MysteryBoxRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(mysteryBox -> {
            if (!mysteryBox.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        mysteryBoxRepository.deleteAllById(ids);
        return true;
    }
}
