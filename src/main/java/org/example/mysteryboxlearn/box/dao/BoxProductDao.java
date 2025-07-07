package org.example.mysteryboxlearn.box.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxProductRel;
import org.example.mysteryboxlearn.box.mapper.MysteryBoxProductRelMapper;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoxProductDao extends ServiceImpl<MysteryBoxProductRelMapper, MysteryBoxProductRel> {
    // 通过 boxId 查找对应的 productIds
    public List<MysteryBoxProductRel> findBoxProducts(String boxId) {
        return lambdaQuery()
                .eq(MysteryBoxProductRel::getMysteryBoxId, Objects.requireNonNull(boxId))
                .list();
    }
}
