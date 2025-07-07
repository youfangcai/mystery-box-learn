package org.example.mysteryboxlearn.box.domain.vo.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MysteryBoxInfoResp {

    private String id;

    private String name;

    private String tips;

    private BigDecimal price;

    private String cover;
}
