package org.example.mysteryboxlearn.vip.root.entity.service;

import org.example.mysteryboxlearn.vip.root.entity.Vip;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class VipService {
    private final VipRepository vipRepository;

}