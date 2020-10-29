package com.allen.utils;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RemoteAddress {



    /**
     * 数量控制
     */
    public static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws  Exception{
       sendCoupon(null);
    	/* //userTest.txt
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
        System.in.read();*/
    }





    public static void sendCoupon(String userId) {
        try {
            String reqUrl = "{    "
            				+ "\"header\" : "
            						+ "{\"service\" : \"com.globalegrow.mlogistics.spi.base.inter.IRemoteInfoService\","
            						+ "\"method\" : \"findRemoteAddressByPage\","
            						+ "\"domain\" : \"\","
            						+ "\"version\":\"1.0.0\","
            						+ "\"tokenId\" : \"13399220594d8a95a9e1334ed21da424\"    "
            						+ "},    "
            				+ "\"body\" : {"
            						+ "\"remoteId\" : 2,"
            						+ "\"pageNo\" : 1 ,"
            						+ "\"pageSize\" : 1"
            					+ "}"
            				+ "}";
            //reqUrl = reqUrl.replace("#USER_ID#", userId);
            System.out.println(reqUrl);
            String result = HttpClientUtil.sendPost("http://gb-obs-outer.gw-soa.com/gateway", reqUrl);
            Integer code = JSON.parseObject(result).getJSONObject("header").getInteger("code");
            if (code != 0) {
                //FileUtils.writeStringToFile(saveErrorReqFile, userId + "\n", true);
                //FileUtils.writeStringToFile(saveErrorReqLogFile, result + "\n", true);
            } else {
            	System.out.println("--------------result:" + result);
                //FileUtils.writeStringToFile(saveSuccessReqFile, userId + "\n", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
