package org.example.mysteryboxlearn.box.root.entity.service;

import org.example.mysteryboxlearn.box.root.entity.MysteryBox;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class MysteryBoxService {
    private final MysteryBoxRepository mysteryBoxRepository;

}