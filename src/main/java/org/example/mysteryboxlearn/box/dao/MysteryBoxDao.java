package org.example.mysteryboxlearn.box.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.mapper.MysteryBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysteryBoxDao extends ServiceImpl<MysteryBoxMapper, MysteryBox> {

    @Autowired
    private MysteryBoxMapper mysteryBoxMapper;

    public IPage<MysteryBox> getMysteryBoxInfoList(IPage<MysteryBox> page) {
        return lambdaQuery().page(page);
    }
}
