package com.cloudtimes.common.utils.uuid;

import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank 序列生成类
 */
public class Seq {
    // 通用序列类型
    public static final String commSeqType = "COMMON";

    // 上传序列类型
    public static final String uploadSeqType = "UPLOAD";

    // 通用接口序列数
    private static AtomicInteger commSeq = new AtomicInteger(1);

    // 上传接口序列数
    private static AtomicInteger uploadSeq = new AtomicInteger(1);

    // 机器标识
    private static String machineCode = "A";

    /**
     * 获取通用序列号
     *
     * @return 序列值
     */
    public static String getId() {
        return getId(commSeqType);
    }

    /**
     * 默认16位序列号 yyMMddHHmmss + 一位机器标识 + 3长度循环递增字符串
     *
     * @return 序列值
     */
    public static String getId(String type) {
        AtomicInteger atomicInt = commSeq;
        if (uploadSeqType.equals(type)) {
            atomicInt = uploadSeq;
        }
        return getId(atomicInt, 3);
    }

    /**
     * 通用接口序列号 yyMMddHHmmss + 一位机器标识 + length长度循环递增字符串
     *
     * @param atomicInt 序列数
     * @param length    数值长度
     * @return 序列值
     */
    public static String getId(AtomicInteger atomicInt, int length) {
        String result = DateUtils.dateTimeNow();
        result += machineCode;
        result += getSeq(atomicInt, length);
        return result;
    }

    /**
     * 序列循环递增字符串[1, 10 的 (length)幂次方), 用0左补齐length位数
     *
     * @return 序列值
     */
    private synchronized static String getSeq(AtomicInteger atomicInt, int length) {
        // 先取值再+1
        int value = atomicInt.getAndIncrement();

        // 如果更新后值>=10 的 (length)幂次方则重置为1
        int maxSeq = (int) Math.pow(10, length);
        if (atomicInt.get() >= maxSeq) {
            atomicInt.set(1);
        }
        // 转字符串，用0左补齐
        return StringUtils.padl(value, length);
    }

    public static synchronized String genNumberTo8Len() {
        Random random = new Random();
        String s = random.nextInt(99999999) + "";   // 添加一个空的字符串使随机数转变为String类型
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 8 - s.length(); i++) {     //生成的随机数可能不是要求的7位的，所以不足的位数用0补齐，确保一定是7位的
            buffer.append("0");
        }
        s = s + buffer.toString();
        return s;
    }

    public static synchronized String genNumberTo5Len() {
        Random random = new Random();
        String s = random.nextInt(99999) + "";   // 添加一个空的字符串使随机数转变为String类型
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 5 - s.length(); i++) {     //生成的随机数可能不是要求的7位的，所以不足的位数用0补齐，确保一定是7位的
            buffer.append("0");
        }
        s = s + buffer.toString();
        return s;
    }

    /**
     * 生成永不重复的订单号
     *
     * @param startLetter 订单号开头字符串
     * @param size        订单号中随机的大写字母个数
     * @return
     */
    public static String createOrderNo(String startLetter, int size) {
        String orderNo = null;
        Date nowDate = new Date();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //生成两位大写字母
        String keyArr = randomLetter(size);
        String fourRandom = random.nextInt(9999) + "";
        int randLength = fourRandom.length();
        //四位随机数，不足四位的补0
        if (fourRandom.length() < 4) {//不足四位的随机数补充0
            for (int i = 1; i <= 4 - randLength; i++) {
                fourRandom = '0' + fourRandom;
            }
        }
        orderNo = startLetter + keyArr + sdf.format(nowDate) + fourRandom;
        return orderNo;
    }

    /**
     * 生成大写字母
     *
     * @param size
     * @return
     */
    public static String randomLetter(int size) {
        String keyArr = "";
        char key = 0;
        boolean[] flag = new boolean[26];    //定义一个Boolean型数组，用来除去重复值
        for (int i = 0; i < size; i++) {     //通过循环为数组赋值
            Random rand = new Random();
            int index;
            do {
                index = rand.nextInt(26);    //随机生成0-25的数字并赋值给index
            } while (flag[index]);    //判断flag值是否为true,如果为true则重新为index赋值
            key = (char) (index + 65);        //大写字母的ASCII值为65-90，所以给index的值加上65，使其符合大写字母的ASCII值区间
            flag[index] = true;       //让对应的flag值为true

            keyArr += key;
        }
        return keyArr;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().hashCode());
        }

    }
}
