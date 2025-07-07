package org.example.mysteryboxlearn.user.root.entity.controller;

import org.example.mysteryboxlearn.user.root.entity.User;

@RestController
@RequestMapping("admin/user")
@AllArgsConstructor
@DefaultFetcherOwner(UserRepository.class)
@SaCheckPermission("/user")
@Transactional
public class UserForAdminController {
    private final UserRepository userRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") User findById(@PathVariable String id) {
        return userRepository.findById(id, UserRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") User> query(@RequestBody QueryRequest<UserSpec> queryRequest) {
        return userRepository.findPage(queryRequest, UserRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated UserInput userInput) {
        return userRepository.save(userInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        userRepository.deleteAllById(ids);
        return true;
    }
}