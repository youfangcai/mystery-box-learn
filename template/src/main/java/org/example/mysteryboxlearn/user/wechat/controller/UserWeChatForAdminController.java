package org.example.mysteryboxlearn.user.wechat.entity.controller;

import org.example.mysteryboxlearn.user.wechat.entity.UserWeChat;

@RestController
@RequestMapping("admin/user-we-chat")
@AllArgsConstructor
@DefaultFetcherOwner(UserWeChatRepository.class)
@SaCheckPermission("/user-we-chat")
@Transactional
public class UserWeChatForAdminController {
    private final UserWeChatRepository userWeChatRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") UserWeChat findById(@PathVariable String id) {
        return userWeChatRepository.findById(id, UserWeChatRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") UserWeChat> query(@RequestBody QueryRequest<UserWeChatSpec> queryRequest) {
        return userWeChatRepository.findPage(queryRequest, UserWeChatRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated UserWeChatInput userWeChatInput) {
        return userWeChatRepository.save(userWeChatInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        userWeChatRepository.deleteAllById(ids);
        return true;
    }
}