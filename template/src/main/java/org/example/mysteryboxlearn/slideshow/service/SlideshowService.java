package org.example.mysteryboxlearn.slideshow.entity.service;

import org.example.mysteryboxlearn.slideshow.entity.Slideshow;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SlideshowService {
    private final SlideshowRepository slideshowRepository;

}