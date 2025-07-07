package org.example.mysteryboxlearn.slideshow.entity.controller;

import org.example.mysteryboxlearn.slideshow.entity.Slideshow;

@RestController
@RequestMapping("admin/slideshow")
@AllArgsConstructor
@DefaultFetcherOwner(SlideshowRepository.class)
@SaCheckPermission("/slideshow")
@Transactional
public class SlideshowForAdminController {
    private final SlideshowRepository slideshowRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Slideshow findById(@PathVariable String id) {
        return slideshowRepository.findById(id, SlideshowRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Slideshow> query(@RequestBody QueryRequest<SlideshowSpec> queryRequest) {
        return slideshowRepository.findPage(queryRequest, SlideshowRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated SlideshowInput slideshowInput) {
        return slideshowRepository.save(slideshowInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        slideshowRepository.deleteAllById(ids);
        return true;
    }
}