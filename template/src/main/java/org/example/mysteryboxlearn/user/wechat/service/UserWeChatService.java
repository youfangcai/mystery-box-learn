package org.example.mysteryboxlearn.user.wechat.entity.service;

import org.example.mysteryboxlearn.user.wechat.entity.UserWeChat;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserWeChatService {
    private final UserWeChatRepository userWeChatRepository;

}