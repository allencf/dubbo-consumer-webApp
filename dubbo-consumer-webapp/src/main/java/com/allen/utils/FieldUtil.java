package com.allen.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(FieldUtil.class);
	
	public <T> void test(Class<T> entity,String propetyName,String propetyValue){
		try {
			Field levelField = entity.getClass().getDeclaredField(propetyName);
			levelField.setAccessible(true);
			if(levelField.getGenericType().toString().equals("class java.lang.Integer")){
			  levelField.set(entity, Integer.parseInt(propetyValue));
			}else if(levelField.getGenericType().toString().equals("class java.lang.Byte")){
			  levelField.set(entity, Byte.parseByte(propetyValue));
			}else if(levelField.getGenericType().toString().equals("class java.lang.String")){
			  levelField.set(entity, propetyValue);
			}
		} catch (Exception e) {
			logger.error("异常信息:" + e.getMessage(), e);
		}
	}
	
	
	
	public <T> void test2(Class<T> entity){
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
		  try 
		  {
		    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),entity.getClass());  
		          Method getMethod = pd.getReadMethod();  //获得get方法  
		          Object o = getMethod.invoke(entity);  //执行get方法返回一个Object  
		          if(o != null){
		           /* MemLevelFirstSettingDetail detail = MemLevelFirstSettingDetail.
		          of(MEM_SETTING_TYPE.LEVEL.getValue(), memLevel.getId(), field.getName(), 
		              o.toString(), Dates.getSecond(Dates.now()));
		      memLevelFristSettingDetailService.add(detail);*/
		          }
		  } 
		  catch (Exception e) {
		    logger.error("获取对象信息异常", e);
		  }
		  
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
