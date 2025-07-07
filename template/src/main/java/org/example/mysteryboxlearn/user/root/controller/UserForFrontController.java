package org.example.mysteryboxlearn.user.root.entity.controller;

import org.example.mysteryboxlearn.user.root.entity.User;

@RestController
@RequestMapping("front/user")
@AllArgsConstructor
@DefaultFetcherOwner(UserRepository.class)
@Transactional
public class UserForFrontController {
    private final UserRepository userRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") User findById(@PathVariable String id) {
        return userRepository.findById(id, UserRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") User> query(@RequestBody QueryRequest<UserSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return userRepository.findPage(queryRequest, UserRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated UserInput userInput) {
        if (StringUtils.hasText(userInput.getId())) {
            User user = userRepository.findById(userInput.getId(), UserRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!user.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return userRepository.save(userInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        userRepository.findByIds(ids, UserRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(user -> {
            if (!user.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        userRepository.deleteAllById(ids);
        return true;
    }
}
