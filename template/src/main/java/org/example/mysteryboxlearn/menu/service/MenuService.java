package org.example.mysteryboxlearn.menu.entity.service;

import org.example.mysteryboxlearn.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;

}