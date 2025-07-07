package org.example.mysteryboxlearn.box.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.vo.request.MysteryBoxReq;
import org.example.mysteryboxlearn.box.domain.vo.response.MysteryBoxInfoResp;
import org.example.mysteryboxlearn.box.service.MysteryBoxService;
import org.example.mysteryboxlearn.box.service.adapter.MysteryBoxAdapter;
import org.example.mysteryboxlearn.common.domain.vo.response.ApiResult;
import org.example.mysteryboxlearn.common.domain.vo.response.PageResult;
import org.example.mysteryboxlearn.common.exception.ResultCode;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/mystery-box")
@RestController
@Slf4j
@AllArgsConstructor
public class MysteryBoxController {

    // 2. 构造方法，推荐
    private final MysteryBoxService mysteryBoxService;

    @GetMapping("/mystery-box-info")
    public ApiResult<MysteryBoxInfoResp> getMysteryBox(@RequestParam String id) {
        MysteryBoxInfoResp resp = mysteryBoxService.getById(id);
        if (resp == null) {
            return ApiResult.success(resp);
        }
        return ApiResult.fail(ResultCode.NotFindError);
    }

    @GetMapping("/mystery-boxs")
    public ApiResult<PageResult<MysteryBoxInfoResp>> getMysteryBoxs(@RequestParam int current, @RequestParam int size) {
        // 分层来实现
        IPage<MysteryBox> page = new Page<>(current, size);
        PageResult<MysteryBoxInfoResp> mysteryBoxInfoList = mysteryBoxService.getMysteryBoxInfoList(page);
        if (mysteryBoxInfoList.getContent() != null) {
            return ApiResult.success(mysteryBoxInfoList);
        }
        return ApiResult.fail(ResultCode.NotFindError);
    }

    @PostMapping("/save")
    public ApiResult<Boolean> insertMysteryBox(@RequestBody MysteryBoxReq mysteryBoxReq) {
        if (mysteryBoxService.insert(mysteryBoxReq)) {
            return ApiResult.success(true);
        }
        return ApiResult.fail(ResultCode.SaveError);
    }

    @PutMapping("/update")
    public ApiResult<Boolean> updateMysteryBox(@RequestParam String id, @RequestBody MysteryBoxReq mysteryBoxReq) {
        MysteryBox mysteryBox = MysteryBoxAdapter.INSTANCE.convertToMb(mysteryBoxReq);
        mysteryBox.setId(id);
        log.info(mysteryBox.toString());
        if (mysteryBoxService.updateById(mysteryBox)) {
            return ApiResult.success();
        }
        return ApiResult.fail(ResultCode.UpdateError);
    }
}
