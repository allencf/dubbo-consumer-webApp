package com.allen.test.sf;

public class MaoPao {
	
    static int[] args = {2,7,3,8,9,6,1};
	
	public static void maoPao1() {
		
		for (int i = 0; i < args.length -1; i++) {
			
			for (int j = 0; j < args.length-1-i; j++) {
				
				if(args[j] < args[j+1]) {
					int temp = args[j];
					args[j] = args[j+1];
					args[j+1] = temp;
				}
			}
		}
		for (int i : args) {
			System.out.println(i);
		}
		
	}
	

	public static void main(String[] a) {
		maoPao1();
	}

}
