package com.allen.test.sf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SuDuKu {
	
	
	/**
	 * 给定一个1-9的数判断，判断在数独中是否合法
	 * @param args   数独
	 * @param value  需要校验的数
	 * @param line   当前数所在的行
	 * @param col    当前数所在的列
	 * @return
	 */
	public static boolean checkLine(int[][] args,int value,int line,int col) {
		if (args.length ==0 || value ==0 || value >9) {
			return false;
		}
		//校验行是否合法
		for (int i = 0; i < 9; i++) {
			if (args[line-1][i] == value) {
				return false;
			} 
		}
		
		//校验列是否合法
		for (int i = 0; i < 9; i++) {
			if (args[i][col-1] == value) {
				return false;
			}
		}
		
		//校验3x3格式是否合法
		int pline = line % 3 ==0 ? line -1 : line;
		int pcol  = col % 3  ==0 ? col -1  : col;
		for (int i = pline / 3 * 3 + 1 ; i < pline / 3 * 3 + 3; i++) {
			for (int j = pcol / 3 * 3 + 1; j < pcol / 3 * 3 + 3; j++) {
				if (args[i-1][j-1] == value) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	/**
	 * 校验是否为数独
	 * @param args
	 * @return
	 */
	public static boolean suDuCheck(int[][] args) {
		if (args.length == 0) {
			return false;
		}
		
		//校验行是否合法
		for (int line = 0; line < 9; line++) {
			int[] p = new int[9];
			for (int col = 0; col < 9; col++) {
				if (p[args[line][col]-1] >0) {
					return false;
				} else {
					p[args[line][col]-1] =1;
				}
			}
		}
		
		//校验列是否合法
		for (int line = 0; line < 9; line++) {
			int[] p = new int[9];
			for (int col = 0; col < 9; col++) {
				if (p[args[col][line]-1] >0) {
					return false;
				}else {
					p[args[col][line]-1] = 1;
				}
			}
		}
		
		//校验3x3格式是否合法
		for (int line = 0; line < 9; line+=3) {
			for (int col = 0; col < 9; col+=3) {
				int[] p = new int[9];
				for(int aline = line ; aline < line +3; aline++) {
					for (int acol = col; acol < col + 3; acol++) {
						if (p[args[aline][acol]-1] >0) {
							return false;
						}else {
							p[args[aline][acol]-1] = 1;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 *  创建一个数独
	 */
	public static void createSuDu() {
		int[][] args = new int[9][9];
		
		boolean flag = true;
		for (int line = 1; line <= 9; line++) {
			for (int col = 1; col <= 9; col++) {
				//校验数独是否合法
				flag = false;
				//生成一个随机数
				int value = createRandom();
				flag = checkLine(args, value,line,col);
				args[line-1][col-1] = value;
				if (!flag) break;
			}
			if (!flag) break;
		}
		
		if (!flag) createSuDu();
		

		//输出数独
		for (int i = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				System.out.println(args[i][j] + " ");
			}
		}
		
		
	}
	
	
	/**
	 * 生成1-9的随机数
	 * @return
	 */
	public static int createRandom() {
		int i = (int)(Math.random()*9) + 1;
		//System.out.println("生成的随机数:" + i );
		return i;
	}
	
	

	public static void main(String[] args) throws IOException {
		/*InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader read = new BufferedReader(in);
		
		int[][] sudu = new int[9][9];
		for (int line = 0; line < 9; line++) {
			String[] arg = read.readLine().split("\\ ");
			for (int col = 0; col < 9; col++) {
				sudu[line][col] = Integer.parseInt(arg[col]);
			}
		}*/
		//System.out.println(suDuCheck(sudu));
		//createSuDu();
		
	}

}
