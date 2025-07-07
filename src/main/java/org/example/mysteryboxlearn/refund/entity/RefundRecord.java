package org.example.mysteryboxlearn.refund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 退款记录
 */
@Data
@TableName(value = "refund_record")
public class RefundRecord extends BaseEntity {

    /**
     * 基础订单id
     */
    @TableField(value = "order_id")
    String orderId;

    /**
     * 退款理由
     */
    @TableField(value = "reason")
    String reason;

    /**
     * 退款金额
     */
    @TableField(value = "amount")
    BigDecimal amount;

    /**
     * 状态
     */
    @TableField(value = "status")
    DictConstants.RefundStatus status;

    /**
     * 退款申请详情
     */
    @TableField(value = "refund_application_details")
    String refundApplicationDetails;

    /**
     * 退款回调详情
     */
    @TableField(value = "refund_notify_details")
    String refundNotifyDetails;

    /**
     * 微信退款订单id
     */
    @TableField(value = "refund_id")
    String refundId;
}

