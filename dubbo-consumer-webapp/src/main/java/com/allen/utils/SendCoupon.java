package com.allen.utils;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SendCoupon {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public  static final String coupon = "79GZ4OLZ19516";
    public static final File saveErrorReqFile = new File("F:\\coupon\\log22\\fail.txt");
    public static final File saveErrorReqLogFile = new File("F:\\coupon\\log22\\failLog.txt");
    public static final File saveSuccessReqFile = new File("F:\\coupon\\log22\\success.txt");
    public static final File saveFilterReqFile = new File("F:\\coupon\\log22\\filter.txt");

    /*      L9XLMF7P11975
            Z0L1PBN295414
    		ANECIIL585660
			79GZ4OLZ19516*/

    /**
     * 数量控制
     */
    public static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws  Exception{
        //userTest.txt
        List<String> list = FileUtils.readLines(new File("F:\\coupon\\userTest.txt"));
        List<String> filterList = FileUtils.readLines(new File("F:\\coupon\\filterUser.txt"));
        HashSet filterSet = new HashSet(filterList);
        for(String line : list) {
            if(filterSet.contains(line.trim())) {
                FileUtils.writeStringToFile(saveFilterReqFile, line + "\n", true);
                continue;
            }
            num.incrementAndGet();
            InnerThread thread = new InnerThread(line);
            executorService.submit(thread);
            if(num.get() > 1000) {
                Thread.sleep(1000);
            }
        }
        // 睡眠主线程
        Thread.sleep(1000 * 60 * 60 * 1000);
        System.in.read();
    }

    public static class InnerThread extends Thread {
        String userId;

        public InnerThread( String userId) {
            this.userId = userId;
        }

        public void run() {
            try {
                sendCoupon(userId);
                num.decrementAndGet();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }





    public static void sendCoupon(String userId) {
        try {
            String reqUrl = "{    \"header\" : {\"service\" : \"com.globalegrow.spi.promotion.common.inter.CouponApi\",\"method\" : \"generateSystemCoupon\",\"domain\" : \"\",\"version\":\"1.0.0\",\"tokenId\" : \"1548eb0d7398d0e2abf65b0f33331d3d\"    },    \"body\" : {\"siteCode\" : \"GB\",\"templateCode\" : \"" + coupon + "\",\"userId\" : #USER_ID#,\"couponResource\":\"10\"    }}";
            reqUrl = reqUrl.replace("#USER_ID#", userId);
            System.out.println(reqUrl);
            String result = HttpClientUtil.sendPost("http://gb-mall-outer.gw-soa.com/gateway", reqUrl);
            Integer code = JSON.parseObject(result).getJSONObject("header").getInteger("code");
            if (code != 0) {
                FileUtils.writeStringToFile(saveErrorReqFile, userId + "\n", true);
                FileUtils.writeStringToFile(saveErrorReqLogFile, result + "\n", true);
            } else {
                FileUtils.writeStringToFile(saveSuccessReqFile, userId + "\n", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
