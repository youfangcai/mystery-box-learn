package org.example.mysteryboxlearn.box.domain.vo.response;

import lombok.Data;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.product.domain.entity.Product;

import java.util.List;

@Data
public class BoxProductRelResp {
    public BoxProductRelResp(MysteryBox mysteryBox, List<Product> products) {

        this.mysteryBoxInfoResp = mysteryBox;
        this.products = products;
    }

    //box
    private MysteryBox mysteryBoxInfoResp;

    // products
    private List<Product> products;
}
