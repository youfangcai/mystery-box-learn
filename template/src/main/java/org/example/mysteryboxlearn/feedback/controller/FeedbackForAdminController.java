package org.example.mysteryboxlearn.feedback.entity.controller;

import org.example.mysteryboxlearn.feedback.entity.Feedback;

@RestController
@RequestMapping("admin/feedback")
@AllArgsConstructor
@DefaultFetcherOwner(FeedbackRepository.class)
@SaCheckPermission("/feedback")
@Transactional
public class FeedbackForAdminController {
    private final FeedbackRepository feedbackRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Feedback findById(@PathVariable String id) {
        return feedbackRepository.findById(id, FeedbackRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Feedback> query(@RequestBody QueryRequest<FeedbackSpec> queryRequest) {
        return feedbackRepository.findPage(queryRequest, FeedbackRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated FeedbackInput feedbackInput) {
        return feedbackRepository.save(feedbackInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        feedbackRepository.deleteAllById(ids);
        return true;
    }
}