package org.example.mysteryboxlearn.box.controller.front;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mysteryboxlearn.box.domain.vo.response.BoxProductRelResp;
import org.example.mysteryboxlearn.box.service.MysteryBoxProductRelService;
import org.example.mysteryboxlearn.common.domain.vo.response.ApiResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/box-product")
@Slf4j
@AllArgsConstructor
public class BoxProductController {
    private final MysteryBoxProductRelService mysteryBoxProductRelService;

    @GetMapping("/find_by_id")
    public ApiResult<BoxProductRelResp> getBoxProduct(@RequestParam String id) {
        return ApiResult.success(mysteryBoxProductRelService.getBoxProduct(id));
    }
}
