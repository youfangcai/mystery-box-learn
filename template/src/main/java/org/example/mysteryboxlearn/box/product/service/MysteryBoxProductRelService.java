package org.example.mysteryboxlearn.box.product.entity.service;

import org.example.mysteryboxlearn.box.product.entity.MysteryBoxProductRel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class MysteryBoxProductRelService {
    private final MysteryBoxProductRelRepository mysteryBoxProductRelRepository;

}