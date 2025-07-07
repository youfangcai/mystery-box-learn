package org.example.mysteryboxlearn.box.category.entity.service;

import org.example.mysteryboxlearn.box.category.entity.MysteryBoxCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class MysteryBoxCategoryService {
    private final MysteryBoxCategoryRepository mysteryBoxCategoryRepository;

}