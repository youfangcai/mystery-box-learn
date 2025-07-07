package org.example.mysteryboxlearn.dict.entity.service;

import org.example.mysteryboxlearn.dict.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class DictService {
    private final DictRepository dictRepository;

}