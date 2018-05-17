package com.allen.test.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.allen.test.Test;

public class TestSerializable {
	
	public void outPut(String filePath){
		
	}
	
	public static void main(String[] args) {
		
		Test test1= new Test();
		test1.setName("allen1");
		
		Test test2 = new Test();
		test2.setName("allen2");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("F:\\test2.txt"));
			out.writeObject("test1名称:");
			out.writeObject(test1);
			out.writeObject("test2名称:");
			out.writeObject(test2);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
