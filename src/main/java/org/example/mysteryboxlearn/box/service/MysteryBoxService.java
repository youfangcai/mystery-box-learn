package org.example.mysteryboxlearn.box.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.vo.request.MysteryBoxReq;
import org.example.mysteryboxlearn.box.domain.vo.response.MysteryBoxInfoResp;
import org.example.mysteryboxlearn.common.domain.vo.response.PageResult;


public interface MysteryBoxService extends IService<MysteryBox> {
    public MysteryBoxInfoResp getById(String id);

    public PageResult<MysteryBoxInfoResp> getMysteryBoxInfoList(IPage<MysteryBox> page);

    public Boolean insert(MysteryBoxReq mysteryBoxReq);
}
