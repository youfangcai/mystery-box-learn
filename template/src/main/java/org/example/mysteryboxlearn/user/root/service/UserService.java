package org.example.mysteryboxlearn.user.root.entity.service;

import org.example.mysteryboxlearn.user.root.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

}