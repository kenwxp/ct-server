package com.cloudtimes.partner.pay.shouqianba.domain;

public class ShouqianbaConstant {
    public static final String apiHost = "https://vsi-api.shouqianba.com"; //支付接口相关

    public static final String activateTerminal = "/terminal/activate";
    public static final String checkinTerminal = "/terminal/checkin";
    public static final String b2CPay = "/upay/v2/pay";
    public static final String queryPayOrder = "/upay/v2/query";
    public static final String cancelPayOrder = "/upay/v2/cancel";
    public static final String getWxpayFaceAuthinfo = "/upay/support/getWxpayfaceAuthinfo";

    public static final String cisApiHost = "https://vapi.shouqianba.com"; //商户接口相关
    public static final String queryMerchantsApply = "/api/cis/v1/merchants/apply/query";


    //通讯码 200：通讯成功；400：客户端错误；500:服务端错误
    public static final String response200 = "200";
    public static final String response400 = "400";
    public static final String response500 = "500";
    //通讯错误码
    public static final String errorInvalidParam = "INVALID_PARAMS"; //参数错误
    public static final String errorInvalidTerminal = "INVALID_TERMINAL";    //终端错误
    public static final String errorIllegalSign = "ILLEGAL_SIGN"; //签名错误
    public static final String errorUnknownSystemError = "UNKNOWN_SYSTEM_ERROR";//系统错误
    //业务返回码
    public static final String busiSuccess = "SUCCESS";   //	业务处理成功
    public static final String busiFail = "FAIL";//	业务处理失败
    public static final String busiPaySuccess = "PAY_SUCCESS";      //	支付操作成功	银货两讫
    public static final String busiPayFail = "PAY_FAIL";   //	支付操作失败并且已冲正	重新进行一笔交易
    public static final String busiPayInProgress = "PAY_IN_PROGRESS";         //	支付中	调用查询接口查询
    public static final String busiPayFailError = "PAY_FAIL_ERROR";        //	支付操作失败并且不确定第三方支付通道状态	联系客服
    public static final String busiPayFailInProgress = "PAY_FAIL_IN_PROGRESS";      //	支付操作失败中并且不清楚状态	联系客服
    public static final String busiCancelSuccess = "CANCEL_SUCCESS";  //	撤单操作成功
    public static final String busiCancelError = "CANCEL_ERROR";//	撤单操作失败并且不确定第三方支付通道状态	联系客服
    public static final String busiCancelAbortError = "CANCEL_ABORT_ERROR";     //	撤单操作试图终止进行中的支付流程，但是失败，不确定第三方支付通道的状态	联系客服
    public static final String busiCancelAbortSuccess = "CANCEL_ABORT_SUCCESS";   //	撤单操作试图终止进行中的支付流程并且成功
    public static final String busiCancelInProgress = "CANCEL_IN_PROGRESS"; //	撤单进行中调用查询接口进行查询
    public static final String busiCancelAbortInProgress = "CANCEL_ABORT_IN_PROGRESS";  //	撤单操作试图终止进行中的支付流程，但是撤单状态不明确
    public static final String busiPrecreateSuccess = "PRECREATE_SUCCESS";    //	预下单操作成功
    public static final String busiPrecreateFail = "PRECREATE_FAIL"; //	预下单操作失败
    public static final String busiPrecreateFailError = "PRECREATE_FAIL_ERROR";    //	预下单状态失败并且不确定第三方支付通道状态	联系客服
    public static final String busiPrecreateFailInProgress = "PRECREATE_FAIL_IN_PROGRESS"; //	预下单状态失败并且不清楚状态	联系客服
    // 业务错误码
    public static final String busiErrInvalidBarcode = "INVALID_BARCODE";  //	条码错误
    public static final String busiErrInsufficientFund = "INSUFFICIENT_FUND";    //	账户金额不足
    public static final String busiErrExpiredBarcode = "EXPIRED_BARCODE";  //	过期的支付条码
    public static final String busiErrBuyerOverDailyLimit = "BUYER_OVER_DAILY_LIMIT";       //	付款人当日付款金额超过上限
    public static final String busiErrBuyerOverTransactionLimit = "BUYER_OVER_TRANSACTION_LIMIT"; //	付款人单笔付款金额超过上限
    public static final String busiErrSellerOverDailyLimit = "SELLER_OVER_DAILY_LIMIT"; //	收款账户当日收款金额超过上限
    public static final String busiErrTradeNotExist = "TRADE_NOT_EXIST";  //	交易不存在
    public static final String busiErrTradeHasSuccess = "TRADE_HAS_SUCCESS";   //	交易已被支付
    public static final String busiErrSellerBalanceNotEnough = "SELLER_BALANCE_NOT_ENOUGH";   //	卖家余额不足
    public static final String busiErrRefundAmtNotEqualTotal = "REFUND_AMT_NOT_EQUAL_TOTAL";   //	退款金额无效
    public static final String busiErrTradeFailed = "TRADE_FAILED";   //	交易失败
    public static final String busiErrUnexpectedProviderError = "UNEXPECTED_PROVIDER_ERROR";     //	不认识的支付通道
    public static final String busiErrTradeTimeout = "TRADE_TIMEOUT";          //	交易超时自动撤单
    public static final String busiErrAccountBalanceNotEnough = "ACCOUNT_BALANCE_NOT_ENOUGH";   //	商户余额不足
    public static final String busiErrClientSnConflict = "CLIENT_SN_CONFLICT"; //	client_sn在系统中已存在
    public static final String busiErrUpayOrderNotExists = "UPAY_ORDER_NOT_EXISTS";  //	订单不存在
    public static final String busiErrRefundableAmountNotEnough = "REFUNDABLE_AMOUNT_NOT_ENOUGH"; //	订单可退金额不足
    public static final String busiErrUpayTerminalNotExists = "UPAY_TERMINAL_NOT_EXISTS";   //	终端号在交易系统中不存在
    public static final String busiErrUpayTerminalStatusAbnormal = "UPAY_TERMINAL_STATUS_ABNORMAL";  //	终端未激活
    public static final String busiErrUpayCancelOrderNoop = "UPAY_CANCEL_ORDER_NOOP";  //	无效操作，订单已经是撤单状态了
    public static final String busiErrUpayCancelInvalidOrderState = "UPAY_CANCEL_INVALID_ORDER_STATE"; //	当前订单状态不可撤销
    public static final String busiErrUpayRefundOrderNoop = "UPAY_REFUND_ORDER_NOOP";   //	无效操作，本次退款退款已经完成了
    public static final String busiErrUpayRefundInvalidOrderState = "UPAY_REFUND_INVALID_ORDER_STATE"; //	当前订单状态不可退款
    public static final String busiErrUpayStoreOverDailyLimit = "UPAY_STORE_OVER_DAILY_LIMIT";  //	商户日收款额超过上限
    public static final String busiErrUpayTcpOrderNotRefundable = "UPAY_TCP_ORDER_NOT_REFUNDABLE";  //	订单参与了活动并且无法撤销
    // 订单状态
    public static final String orderCreated = "CREATED";   //	订单已创建/支付中
    public static final String orderPaid = "PAID";//	最终状态 订单支付成功
    public static final String orderPayCanceled = "PAY_CANCELED";       //	最终状态 支付失败并且已经成功充正
    public static final String orderPayError = "PAY_ERROR";    //	支付异常，不确定是否已经成功充正,请联系收钱吧客服确认是否支付成功
    public static final String orderRefunded = "REFUNDED";    //	最终状态 已成功全额退款
    public static final String orderPartialRefunded = "PARTIAL_REFUNDED";//	最终状态 已成功部分退款
    public static final String orderRefundInProgress = "REFUND_INPROGRESS"; //	退款进行中
    public static final String orderRefundError = "REFUND_ERROR";    //	退款异常并且不确定第三方支付通道的最终退款状态
    public static final String orderCanceled = "CANCELED"; //	最终状态 客户端发起的撤单已成功
    public static final String orderCancelError = "CANCEL_ERROR";    //	客户端发起的撤单异常并且不确定第三方支付通道的最终状态
    public static final String orderCancelInProgress = "CANCEL_INPROGRESS";//	撤单进行中
    public static final String orderInvalidStatusCode = "INVALID_STATUS_CODE"; //	无效的状态码
    // 流水状态
    public static final String flowSuccess = "SUCCESS"; //	业务执行确认成功（即收钱吧后台和消费者端均成功）	银货两讫（无论是交货还是退货）
    public static final String flowFailCanceled = "FAIL_CANCELED";//	确认失败（即收钱吧后台和消费者端均失败）	银货两讫，（不交货或是不退货）
    public static final String flowFailProtocol1 = "FAIL_PROTOCOL_1";//	协议错误	小概率事件，失败但不确认消费者端状态
    public static final String flowFailIo1 = "FAIL_IO_1";//	IO错误	同上
    public static final String flowFailProtocol2 = "FAIL_PROTOCOL_2"; //	协议错误	同上
    public static final String flowFailIo2 = "FAIL_IO_2"; //	IO错误	同上
    public static final String flowFailProtocol3 = "FAIL_PROTOCOL_3";//	协议错误	同上
    public static final String flowFailError = "FAIL_ERROR"; //	支付流程失败后进行自动撤单操作，和支付通道通信成功，但是返回结果为撤单失败。	同上
    public static final String flowCancelError = "CANCEL_ERROR"; //	撤单流程调用支付通道的撤单接口通信成功，但是返回结果为撤单失败。	同上
    public static final String flowRefundError = "REFUND_ERROR"; //	退款流程调用支付通道的退款接口通信成功，但是返回的结果为退款失败。	同上
}
