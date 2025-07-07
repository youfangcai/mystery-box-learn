package org.example.mysteryboxlearn.box.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.reflection.MetaObject;
import org.example.mysteryboxlearn.box.dao.MysteryBoxDao;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.vo.request.MysteryBoxReq;
import org.example.mysteryboxlearn.box.domain.vo.response.MysteryBoxInfoResp;
import org.example.mysteryboxlearn.box.mapper.MysteryBoxMapper;
import org.example.mysteryboxlearn.box.service.MysteryBoxService;
import org.example.mysteryboxlearn.box.service.adapter.MysteryBoxAdapter;
import org.example.mysteryboxlearn.common.base.BaseEntityHandler;
import org.example.mysteryboxlearn.common.domain.vo.response.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysteryBoxServiceImpl extends ServiceImpl<MysteryBoxMapper, MysteryBox> implements MysteryBoxService {
    @Autowired
    private MysteryBoxDao mysteryBoxDao;

    @Override
    public MysteryBoxInfoResp getById(String id) {
        MysteryBoxInfoResp mysteryBoxInfoResp = new MysteryBoxInfoResp();
        MysteryBox mysteryBox = mysteryBoxDao.getById(id);
        mysteryBoxInfoResp.setId(mysteryBox.getId());
        mysteryBoxInfoResp.setName(mysteryBox.getName());
        mysteryBoxInfoResp.setCover(mysteryBox.getCover());
        mysteryBoxInfoResp.setPrice(mysteryBox.getPrice());
        return mysteryBoxInfoResp;
    }

    @Override
    public PageResult<MysteryBoxInfoResp> getMysteryBoxInfoList(IPage<MysteryBox> page) {
        IPage<MysteryBox> mysteryBoxInfoList = mysteryBoxDao.getMysteryBoxInfoList(page);
        IPage<MysteryBoxInfoResp> respIPage = mysteryBoxInfoList.convert(MysteryBoxAdapter.INSTANCE::convertToResp);
        return new PageResult<MysteryBoxInfoResp>().setContent(respIPage.getRecords());
    }

    @Override
    public Boolean insert(MysteryBoxReq mysteryBoxReq){
        if (mysteryBoxDao.save(MysteryBoxAdapter.INSTANCE.convertToMb(mysteryBoxReq))){
            return true;
        }
        return false;
    }
}
