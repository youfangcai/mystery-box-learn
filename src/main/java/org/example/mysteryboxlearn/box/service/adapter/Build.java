package org.example.mysteryboxlearn.box.service.adapter;

import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.vo.response.BoxProductRelResp;
import org.example.mysteryboxlearn.product.domain.entity.Product;

import java.util.List;

public class Build {
    // 构建BoxProductResp vo
    public static BoxProductRelResp buildBoxProductResp(MysteryBox mysteryBox, List<Product> products) {
        return new BoxProductRelResp(mysteryBox, products);
    }
}
