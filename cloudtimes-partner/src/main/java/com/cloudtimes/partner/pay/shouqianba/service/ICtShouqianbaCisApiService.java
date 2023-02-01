package com.cloudtimes.partner.pay.shouqianba.service;

import java.util.Map;

/**
 * 收钱吧客户关系接口服务
 */
public interface ICtShouqianbaCisApiService {
    /**
     * 查询商户申请记录
     *
     * @param merchantsNo 本系统商户号，用于关联收钱吧商户
     * @return map
     * "client_id"           String //	客户编号	Y	客户编号
     * "client_merchant_sn"  String //	客户系统商户号	Y	客户系统商户号
     * "merchant_sn"         String //	收钱吧商户号	N	入网成功后返回该字段
     * "name"                String //	商户名称	Y	提交申请时传入的商户名称
     * "status"              String //	申请状态	Y	1:入网中、2:待账户验证、3:待签约、4:入网成功、5：开通中、6：待店铺认证、9:已驳回
     * "sign_url"            String //	签约链接	N	1、请联系人用微信扫码打开签约链接，根据页面指引完成绑定微信号、账户验证、签约等操作2、绑定微信号后，后续申请单进度可通过公众号自动通知联系人
     * "reject_reason"       String //	驳回原因	N	驳回说明
     * "merchant_qrcode"     String //	商户二维码	N	申请状态为待店铺认证时返回，需商户实地拍摄门店照片
     * "audit_detail"    List   //	审核详情	N	各资料驳回原因，当申请状态为已驳回时，才返回该字段
     * * "field"         // 驳回信息属性名
     * * "field_name"    // 驳回信息名称
     * * "reject_reason" // 驳回原因
     */
    public Map<String, Object> queryMerchantsApply(String merchantsNo);

}
