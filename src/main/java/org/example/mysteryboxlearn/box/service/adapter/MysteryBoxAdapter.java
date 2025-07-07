package org.example.mysteryboxlearn.box.service.adapter;

import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.vo.request.MysteryBoxReq;
import org.example.mysteryboxlearn.box.domain.vo.response.BoxProductRelResp;
import org.example.mysteryboxlearn.box.domain.vo.response.MysteryBoxInfoResp;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MysteryBoxAdapter {
    public static MysteryBoxAdapter INSTANCE = Mappers.getMapper(MysteryBoxAdapter.class);

    // MysteryBox to MysteryBoxInfoResp
    public MysteryBoxInfoResp convertToResp(MysteryBox mb);
    // MysteryBoxReq to MysteryBox
    public MysteryBox convertToMb(MysteryBoxReq req);

    public BoxProductRelResp convertToResp(MysteryBox box, Product product);
}
