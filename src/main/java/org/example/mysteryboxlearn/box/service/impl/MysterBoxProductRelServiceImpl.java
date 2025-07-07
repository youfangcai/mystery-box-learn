package org.example.mysteryboxlearn.box.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.example.mysteryboxlearn.box.dao.BoxProductDao;
import org.example.mysteryboxlearn.box.dao.MysteryBoxDao;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxProductRel;
import org.example.mysteryboxlearn.box.domain.vo.response.BoxProductRelResp;
import org.example.mysteryboxlearn.box.mapper.MysteryBoxProductRelMapper;
import org.example.mysteryboxlearn.box.service.MysteryBoxProductRelService;
import org.example.mysteryboxlearn.box.service.adapter.Build;
import org.example.mysteryboxlearn.product.dao.ProductDao;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MysterBoxProductRelServiceImpl extends ServiceImpl<MysteryBoxProductRelMapper, MysteryBoxProductRel> implements MysteryBoxProductRelService {

    private final BoxProductDao boxProductDao;
    private final ProductDao productDao;
    private final MysteryBoxDao mysteryBoxDao;

    // 查找boxId对应的商品们
    @Override
    public BoxProductRelResp getBoxProduct(String boxId) {
        List<MysteryBoxProductRel> boxProducts = boxProductDao.findBoxProducts(boxId);
        List<String> productIds = boxProducts.stream()
                .map(MysteryBoxProductRel::getProductId)
                .collect(Collectors.toList());
        List<Product> products = productDao.getProducts(productIds);
        MysteryBox box = mysteryBoxDao.getById(boxId);
        return Build.buildBoxProductResp(box, products);
    }

    // 通过商品id找到box
//    @Override
//    public
}
