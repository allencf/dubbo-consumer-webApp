package com.allen.test;

public class TestData {
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestData test = new TestData();
		String a = test.getaValue();
		System.out.println(a);

	}

}
