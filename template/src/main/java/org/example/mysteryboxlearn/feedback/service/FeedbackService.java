package org.example.mysteryboxlearn.feedback.entity.service;

import org.example.mysteryboxlearn.feedback.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

}