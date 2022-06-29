package com.all.lin.mybatis.customTenant;

import lombok.*;

import java.util.Date;

/**
 * 支付渠道 DO
 * 一个应用下，会有多种支付渠道，例如说微信支付、支付宝支付等等
 * <p>
 * 即 PayAppDO : PayChannelDO = 1 : n
 *
 * @author 芋道源码
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayChannelDO {

    /**
     * 渠道编号，数据库自增
     */
    private Long id;
    /**
     * 渠道编码
     */
    private String code;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 渠道费率，单位：百分比
     */
    private Double feeRate;
    /**
     * 备注
     */
    private String remark;

    /**
     * 商户编号
     */
    private Long merchantId;
    /**
     * 应用编号
     */
    private Long appId;
    private Date updateTime;

}