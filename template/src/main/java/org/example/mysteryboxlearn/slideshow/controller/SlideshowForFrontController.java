package org.example.mysteryboxlearn.slideshow.entity.controller;

import org.example.mysteryboxlearn.slideshow.entity.Slideshow;

@RestController
@RequestMapping("front/slideshow")
@AllArgsConstructor
@DefaultFetcherOwner(SlideshowRepository.class)
@Transactional
public class SlideshowForFrontController {
    private final SlideshowRepository slideshowRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Slideshow findById(@PathVariable String id) {
        return slideshowRepository.findById(id, SlideshowRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Slideshow> query(@RequestBody QueryRequest<SlideshowSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return slideshowRepository.findPage(queryRequest, SlideshowRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated SlideshowInput slideshowInput) {
        if (StringUtils.hasText(slideshowInput.getId())) {
            Slideshow slideshow = slideshowRepository.findById(slideshowInput.getId(), SlideshowRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!slideshow.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return slideshowRepository.save(slideshowInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        slideshowRepository.findByIds(ids, SlideshowRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(slideshow -> {
            if (!slideshow.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        slideshowRepository.deleteAllById(ids);
        return true;
    }
}
