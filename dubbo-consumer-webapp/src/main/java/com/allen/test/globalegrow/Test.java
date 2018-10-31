package com.allen.test.globalegrow;


import com.allen.utils.HttpClientUtils;

public class Test {
	
	private final static String GATEWAY_URL    = "http://10.60.34.84:2087/gateway/";
	
	private final static String GATEWAY_TOKEN  = "421d2cc706fba3e1a38f42ab2a67d31b";

	private final static String SERVICE_URL    = "com.globalegrow.logistics.spi.common.inter.LogisticsShippingRpcService";
	
	private final static String SERVICE_METHOD = "getActualShippingForPhp";
	
	private final static String SERVICE_VERSION = "1.0.0";
	
	
	private static String getReqParam(String body,String method) {
		String reqParam = "{ "
			+"\"header\":{ "
			+"\"service\":\"" + SERVICE_URL + "\", "
			+"\"method\": \"" + SERVICE_METHOD + "\" ,"
			+"\"domain\":\"\","
			+"\"version\": \"" + SERVICE_VERSION + "\","
			+"\"tokenId\": \"" +  GATEWAY_TOKEN + "\" "
			+"}, "
			+"\"body\":{ "
			+"\"siteCode\":\"GB\", "
			+"\"lang\":\"\", "
			+"\"pipelineCode\":\"GB\","
			+"\"addressInfo\":{ "
			+"\"countryCode\":\"US\", "
			+"\"zipCode\":\"\" "
			+"},"
			+"\"orderInfoList\":[ "
			+"{ "
			+"\"realWhCode\":\"DZWH\", "
			+"\"isReturnFreight\":1, "
			+"\"goodsList\":[ "
			+"{ "
			+"\"goodSn\":\"207702702\", "
			+"\"priceMd5\":\"6C4A40E93675F771627804096C5DA188\", "
			+"\"num\":\"1\", "
			+"\"saleWeight\":1.6, "
			+"\"volumeWeight\":1.3, "
			+"\"saleSizeLong\":1.2, "
			+"\"saleSizeWide\":1.3, "
			+"\"saleSizeHigh\":1.6, "
			+"\"categoryId\":\"123\", "
			+"\"properties\":\"123\", "
			+"\"goodPrice\":null "
			+"}"
			+"]"
			+"}"
			+"]"
			+"}"
			+"}";

		return reqParam;
	}
	
	
	
	public static void callActualShipping() {
		String jsonParam = getReqParam(null, null);
		String result = null;
		try {
			result = HttpClientUtils.doPostByJson(GATEWAY_URL, jsonParam);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ;
	}
	
	
	public static void main(String[] args) {
		callActualShipping();
	}

}
