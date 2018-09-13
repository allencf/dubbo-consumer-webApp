package com.allen.test.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class FileProcess {
	
	private static String FILE_PATH = System.getProperty("user.dir");
	
	
	public static void getFileContent(String fileName) {
		
		try {
			File file = new File(FILE_PATH + fileName + ".txt");
			if (!file.exists()) {
				System.out.println("文件不存在:" + file);
				return;
			}
			@SuppressWarnings("unchecked")
			List<String> list =  FileUtils.readLines(file);
			list.forEach(content -> {
				String[] arr = content.split("\\,");
				if (arr.length < 6) { return; }
				System.out.println(arr[0] + "--" + 
				                   arr[1] + "--" +
				                   arr[2] + "--" +
				                   arr[3] + "--" +
				                   arr[4] + "--" +
				                   arr[5] );
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		String fileName = "/target/classes/file/logistics/logistics_way_relation" ;
		getFileContent(fileName);
		//System.out.println(FILE_PATH);
		
	}

}
