package org.example.mysteryboxlearn.menu.entity.controller;

import org.example.mysteryboxlearn.menu.entity.Menu;

@RestController
@RequestMapping("admin/menu")
@AllArgsConstructor
@DefaultFetcherOwner(MenuRepository.class)
@SaCheckPermission("/menu")
@Transactional
public class MenuForAdminController {
    private final MenuRepository menuRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Menu findById(@PathVariable String id) {
        return menuRepository.findById(id, MenuRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Menu> query(@RequestBody QueryRequest<MenuSpec> queryRequest) {
        return menuRepository.findPage(queryRequest, MenuRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated MenuInput menuInput) {
        return menuRepository.save(menuInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        menuRepository.deleteAllById(ids);
        return true;
    }
}