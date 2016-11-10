/*package com.allen.qd;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.alibaba.fastjson.JSON;
import com.qida.clm.cache.entity.GroupKeyStore;
import com.qida.clm.cache.util.JsonCacheHandler;
import com.qida.common.cache.Cache;
import com.qida.common.entity.AppName;
import com.qida.common.lang.Strings;
import com.qida.learning.common.util.CacheKeyHolder;
import com.qida.systemparameter.utils.SystemParameters;

public class JsonCacheConverter extends MappingJackson2HttpMessageConverter {

	@Autowired
	private JsonCacheHandler jsonCacheHandler;
	@Autowired
	private Cache cache;
	
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		//最终生成json格式的字符串
		String jsonResult;
		//配置生成缓存的Key
		String storeKey;
		//缓存Key组
		String groupKey;
		//顶层Key组
		String topGroupKey;
		
		topGroupKey.isEmpty();
		
		jsonResult = object.toString();
		
		storeKey = CacheKeyHolder.getCurrentStoreKey();
		groupKey = CacheKeyHolder.getCurrentGroupKey();
		topGroupKey = CacheKeyHolder.getCurrentTopGroupKey();
		
		//stroreKey存在，方法执行缓存拦截
		if(storeKey != null){
			beforeExcute(storeKey, groupKey, topGroupKey, jsonResult);
			CacheKeyHolder.clearAll();
		}
		super.writeInternal(object, outputMessage);
	}
	
	private void beforeExcute(String storeKey, String groupKey, String topGroupKey, String jsonResult) {
		//if(jsonCacheHandler.getFrontCacheAble()){
		boolean cacheAble = "Y".equals(SystemParameters.getForString(AppName.CLM, "common", "resourceCacheAble"));
		if(cacheAble){
		    	logger.debug("******************** jsonResult string :" + jsonResult);
		    	cache.add(storeKey, jsonResult);
		    	logger.debug("******************** put jsonResult into memcache ********************");
		    	if(Strings.isNotEmpty(groupKey)){
		    		logger.debug("******************** groupKey ********************:" + groupKey);
		    		//将storeKey保存到groupKey中
		    		String groupKeyResult = (String) cache.get(groupKey);
		    		if(Strings.isNotEmpty(groupKeyResult)){
		    			logger.debug("******************** groupKey exists ********************");
		    			logger.debug("******************** groupKey value ********************");
		    			logger.debug(groupKeyResult);
		    			GroupKeyStore gks = null;
		    			gks = JSON.parseObject(groupKeyResult, GroupKeyStore.class);
		    			gks.getStoreKeys().add(storeKey);
		    			String jsonStr = JSON.toJSONString(gks);
		    			cache.replace(groupKey, jsonStr);
		    			logger.debug(jsonStr);
		    			gks = null;
		    		}else{
		    			logger.debug("******************** groupKey first ********************");
		    			GroupKeyStore gks = new GroupKeyStore();
		    			gks.setGroupKey(groupKey);
		    			gks.getStoreKeys().add(storeKey);
		    			String jsonStr = JSON.toJSONString(gks);
		    			logger.debug("******************** groupKey value ********************");
		    			logger.debug(jsonStr);
		    			//可能memcache分组被顶层分组引用，但是已被删除，所以使用set而不用add
		    			cache.set(groupKey, jsonStr);
		    			gks = null;
		    			
		    			if(Strings.isNotEmpty(topGroupKey)){
		    				logger.debug("******************** topGroupKey ********************:" + topGroupKey);
				    		//将storeKey保存到groupKey中
				    		String topGroupKeyResult = (String) cache.get(topGroupKey);
		    				if(Strings.isNotEmpty(topGroupKeyResult)){
		    					logger.debug("******************** topgroupKey exists ********************");
				    			logger.debug("******************** topgroupKey value ********************");
				    			logger.debug(topGroupKeyResult);
				    			GroupKeyStore topGks = null;
				    			topGks = JSON.parseObject(topGroupKeyResult, GroupKeyStore.class);
				    			topGks.getStoreKeys().add(groupKey);
				    			String topJsonStr = JSON.toJSONString(topGks);
				    			cache.replace(topGroupKey, topJsonStr);
				    			logger.debug(topJsonStr);
				    			topGks = null;
		    				}else{
		    					logger.debug("******************** topGroupKey first ********************");
				    			GroupKeyStore topGks = new GroupKeyStore();
				    			topGks.setGroupKey(topGroupKey);
				    			topGks.getStoreKeys().add(groupKey);
				    			String topJsonStr = JSON.toJSONString(topGks);
				    			logger.debug("******************** topGroupKey value ********************");
				    			logger.debug(topJsonStr);
				    			cache.add(topGroupKey, topJsonStr);
				    			topGks = null;
		    				}
		    			}
		    			
		    		}
		    	}
    	}
	}

}
*/