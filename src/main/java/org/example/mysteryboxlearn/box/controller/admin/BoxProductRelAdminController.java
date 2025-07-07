package org.example.mysteryboxlearn.box.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxProductRel;
import org.example.mysteryboxlearn.box.domain.vo.request.MysteryBoxReq;
import org.example.mysteryboxlearn.box.service.MysteryBoxProductRelService;
import org.example.mysteryboxlearn.common.domain.vo.response.ApiResult;
import org.example.mysteryboxlearn.common.exception.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/box-product-rel")
@Slf4j
@AllArgsConstructor
public class BoxProductRelAdminController {
    private MysteryBoxProductRelService mysteryBoxProductRelService;

    // 通过id
//    @GetMapping()
//    public ApiResult<MysteryBoxProductRel> getById(@RequestParam MysteryBoxReq req) {
//        MysteryBoxProductRel boxProductRel = mysteryBoxProductRelService.
//        if (boxProductRel == null){
//            return ApiResult.fail(ResultCode.NotFindError);
//        }
//        return ApiResult.success()
//    }
}
