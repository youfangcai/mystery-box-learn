package org.example.mysteryboxlearn.carriage.entity.service;

import org.example.mysteryboxlearn.carriage.entity.CarriageTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CarriageTemplateService {
    private final CarriageTemplateRepository carriageTemplateRepository;

}