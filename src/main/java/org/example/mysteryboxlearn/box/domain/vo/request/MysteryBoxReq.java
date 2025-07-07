package org.example.mysteryboxlearn.box.domain.vo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxCategory;

import java.math.BigDecimal;
@Data
public class MysteryBoxReq {

    String name;

    String details;

    String tips; // 预售还是在售

    BigDecimal price;

    String cover; // 封面

    String category; // 盲盒分类
}
