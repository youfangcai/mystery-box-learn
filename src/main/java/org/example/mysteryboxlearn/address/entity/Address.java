package org.example.mysteryboxlearn.address.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * 地址表
 */

@Data
@TableName(value = "address")
public class Address extends BaseEntity {

    /**
     * 维度
     */
    @TableField(value = "latitude")
    double latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    double longitude;

    /**
     * 详细地址
     */
    @TableField(value = "details")
    String details;

    /**
     * 省
     */
    @TableField(value = "province")
    String province;

    /**
     * 市
     */
    @TableField(value = "city")
    String city;

    /**
     * 区
     */
    @TableField(value = "district")
    String district;

    /**
     * 手机号
     */

    @TableField(value = "phone_number")
    String phoneNumber;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    String realName;

    /**
     * 门牌号
     */
    @TableField(value = "house_number")
    String houseNumber;

    /**
     * 是否置顶
     */
    @TableField(value = "top")
    boolean top;
}

