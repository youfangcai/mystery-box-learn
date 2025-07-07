package org.example.mysteryboxlearn.carriage.entity.controller;

import org.example.mysteryboxlearn.carriage.entity.CarriageTemplate;

@RestController
@RequestMapping("admin/carriage-template")
@AllArgsConstructor
@DefaultFetcherOwner(CarriageTemplateRepository.class)
@SaCheckPermission("/carriage-template")
@Transactional
public class CarriageTemplateForAdminController {
    private final CarriageTemplateRepository carriageTemplateRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CarriageTemplate findById(@PathVariable String id) {
        return carriageTemplateRepository.findById(id, CarriageTemplateRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") CarriageTemplate> query(@RequestBody QueryRequest<CarriageTemplateSpec> queryRequest) {
        return carriageTemplateRepository.findPage(queryRequest, CarriageTemplateRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated CarriageTemplateInput carriageTemplateInput) {
        return carriageTemplateRepository.save(carriageTemplateInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        carriageTemplateRepository.deleteAllById(ids);
        return true;
    }
}