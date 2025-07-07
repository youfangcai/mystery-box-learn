package org.example.mysteryboxlearn.box.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxProductRel;
import org.example.mysteryboxlearn.box.domain.vo.response.BoxProductRelResp;

import java.util.List;

public interface MysteryBoxProductRelService extends IService<MysteryBoxProductRel> {
    public BoxProductRelResp getBoxProduct(String boxId);
//    public List<Mys>
}
