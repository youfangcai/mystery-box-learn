package org.example.mysteryboxlearn.user.wechat.entity.controller;

import org.example.mysteryboxlearn.user.wechat.entity.UserWeChat;

@RestController
@RequestMapping("front/user-we-chat")
@AllArgsConstructor
@DefaultFetcherOwner(UserWeChatRepository.class)
@Transactional
public class UserWeChatForFrontController {
    private final UserWeChatRepository userWeChatRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") UserWeChat findById(@PathVariable String id) {
        return userWeChatRepository.findById(id, UserWeChatRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_FRONT") UserWeChat> query(@RequestBody QueryRequest<UserWeChatSpec> queryRequest) {
        queryRequest.getQuery().setCreatorId(StpUtil.getLoginIdAsString());
        return userWeChatRepository.findPage(queryRequest, UserWeChatRepository.COMPLEX_FETCHER_FOR_FRONT);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated UserWeChatInput userWeChatInput) {
        if (StringUtils.hasText(userWeChatInput.getId())) {
            UserWeChat userWeChat = userWeChatRepository.findById(userWeChatInput.getId(), UserWeChatRepository.COMPLEX_FETCHER_FOR_FRONT).orElseThrow(() -> new BusinessException("数据不存在"));
            if (!userWeChat.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能修改自己的数据");
            }
        }
        return userWeChatRepository.save(userWeChatInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        userWeChatRepository.findByIds(ids, UserWeChatRepository.COMPLEX_FETCHER_FOR_FRONT).forEach(userWeChat -> {
            if (!userWeChat.creator().id().equals(StpUtil.getLoginIdAsString())) {
                throw new BusinessException("只能删除自己的数据");
            }
        });
        userWeChatRepository.deleteAllById(ids);
        return true;
    }
}
