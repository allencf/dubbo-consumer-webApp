package com.allen.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Test implements Serializable{
	
	
	private static final long serialVersionUID = -664804447927442405L;

	private Object obj;
	
	private String name;
	
	private Date updateTime;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Test() {
	}
	
	public String getaValue(){
		String value = "";
		try {
			value ="test1";
			return value;
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			value = "test12";
		}
		return value;
	}
	
	public static void erweiShuzu () {
		int[][] args = {{1,2,3},{4,5,6}};
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(args[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	public static void yiweiShuzu () {
		int[] args = new int[9];
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			
		}
		
	}

	protected void test2() {
		
	}

	public static void main(String[] args) {
		/*int i = 0x00000001;
		System.out.println(0 & i);
		//boolean flag = 1 & i;
		
		String s ="aa";
		int code = s.hashCode();
		System.out.println(code);
		Map<String, Object> map = new HashMap<>();
		String value1 = "value1";
		String v = (String) map.put("key1", value1);
		System.out.println(v);
		
		Test test = new Test();
		test.setName("allen");
		
		System.out.println(test.getName());
		
		Test test2 = test;
		test2.setName("viney");
		
		System.out.println(test.getName());
		*/
		//int a = (int)3.9;
		
		//System.out.println(a);
		
		
		//long temp = (int)3.9;
		
		//temp%=2;
		//System.out.println(temp);
		
		//System.out.println(4&7);
		
		//System.out.println(Math.round(10.2));
		
		//Math.round(12.376*Math.round(10.2))/Math.round(10.2);	
		
		/*List<Test> list = new ArrayList<>();
		Test test = new Test();
		test.setName("");
		test.setUpdateTime(null);
		list.add(test);i 
		
		JSONObject object = new JSONObject();
		object.put("data", list);
		
		JSON json = JSONSerializer.toJSON(object);
		
		System.out.println(json);*/
		String s = "";
		
		int i = 1;
		//System.out.println("i++ :" + i++ + ",i:" + i);
		//System.out.println("++i :" + ++i + ",i" + i);
		//erweiShuzu();
		yiweiShuzu();
	}

}
