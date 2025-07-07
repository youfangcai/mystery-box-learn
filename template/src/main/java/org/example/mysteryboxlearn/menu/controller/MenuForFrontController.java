package org.example.mysteryboxlearn.menu.entity.controller;

import org.example.mysteryboxlearn.menu.entity.Menu;

@RestController
@RequestMapping("front/menu")
@AllArgsConstructor
@DefaultFetcherOwner(MenuRepository.class)
@Transactional
public class MenuForFrontController {
    private final MenuRepository menuRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Menu findById(@PathVariable String id) {
        return menuRepository.findById(id, MenuRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") Menu> query(@RequestBody QueryRequest<MenuSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return menuRepository.findPage(queryRequest, MenuRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MenuInput menuInput) {
        if (StringUtils.hasText(menuInput.getId())) {
            Menu menu = menuRepository.findById(menuInput.getId(), MenuRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!menu.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return menuRepository.save(menuInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        menuRepository.findByIds(ids, MenuRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(menu -> {
            if (!menu.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        menuRepository.deleteAllById(ids);
        return true;
    }
}
