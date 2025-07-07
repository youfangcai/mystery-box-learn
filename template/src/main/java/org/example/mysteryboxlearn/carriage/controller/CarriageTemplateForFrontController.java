package org.example.mysteryboxlearn.carriage.entity.controller;

import org.example.mysteryboxlearn.carriage.entity.CarriageTemplate;

@RestController
@RequestMapping("front/carriage-template")
@AllArgsConstructor
@DefaultFetcherOwner(CarriageTemplateRepository.class)
@Transactional
public class CarriageTemplateForFrontController {
    private final CarriageTemplateRepository carriageTemplateRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") CarriageTemplate findById(@PathVariable String id) {
        return carriageTemplateRepository.findById(id, CarriageTemplateRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") CarriageTemplate> query(@RequestBody QueryRequest<CarriageTemplateSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return carriageTemplateRepository.findPage(queryRequest, CarriageTemplateRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CarriageTemplateInput carriageTemplateInput) {
        if (StringUtils.hasText(carriageTemplateInput.getId())) {
            CarriageTemplate carriageTemplate = carriageTemplateRepository.findById(carriageTemplateInput.getId(), CarriageTemplateRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!carriageTemplate.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return carriageTemplateRepository.save(carriageTemplateInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        carriageTemplateRepository.findByIds(ids, CarriageTemplateRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(carriageTemplate -> {
            if (!carriageTemplate.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        carriageTemplateRepository.deleteAllById(ids);
        return true;
    }
}
