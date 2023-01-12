package com.cloudtimes.common.utils;

import com.cloudtimes.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HolidayUtil {

    static Logger log = LoggerFactory.getLogger(HolidayUtil.class);

    /**
     * 工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2
     *
     * @param httpArg
     * @return
     */
    public static boolean isWeekday(String httpArg) {
        String url = "https://tool.bitefu.net/jiari/?d=" + httpArg;
        String result = HttpUtils.sendGet(url);
        log.info("日期检测返回当前是否为工作日：" + result);
        return Integer.parseInt(result) == 0 ? true : false;
    }

    public static void main(String[] args) {
        //判断今天是否是工作日 周末 还是节假日
        Date nowTime = DateUtils.getNowDate();
        String day = DateUtils.parseDateToStr("yyyyMMdd", nowTime);
        System.out.println(day);
        boolean n = isWeekday(day);
        System.out.println(n);
        //工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2
    }

}
