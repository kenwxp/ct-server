package com.cloudtimes.common.sms.cdcxcloud.api;

import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.sms.cdcxcloud.constant.ApiRequestMethodEnum;
import com.cloudtimes.common.sms.cdcxcloud.constant.SmsActionEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 创信 短信渠道商
 */
public class CdcxcloundSMSUtil {


    private static LinkedBlockingQueue<SMSWork> smsQueue = new LinkedBlockingQueue<>();


    private static Thread sendThread;

    public static synchronized void semdSms(String body, String phone) {
        if (sendThread == null || !sendThread.isAlive()) {
            sendThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            SMSWork task = smsQueue.take();
                            task.run();
                        } catch (Exception ex) {

                        }
                    }
                }
            });
            sendThread.setDaemon(true);
            sendThread.start();
        }

        SMSWork smsWork = new SMSWork(body, phone);
        try {
            CdcxcloundSMSUtil.smsQueue.put(smsWork);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendToSms(String body, String phone) {
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("account", "922459");
        p.put("password", "8eYtxi");
        p.put("mobile", phone);
        p.put("content", body);
        p.put("extno", "10690468");
        //接入码
        SmsHttpApi smsHttpApi = new SmsHttpApi(ApiRequestMethodEnum.get);
        try {
            //发短信
            String result = "";
            System.out.println("发送短信,接收手机：" + phone + "内容:[" + body + "]");
            result = smsHttpApi.doAction(p, SmsActionEnum.send);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("发送短信失败！");
        }
    }
}


class SMSWork implements Runnable {

    private String body;

    private String phone;

    public SMSWork(String body, String phone) {
        this.body = body;
        this.phone = phone;
    }

    @Override
    public void run() {
        CdcxcloundSMSUtil.sendToSms(this.body, this.phone);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
