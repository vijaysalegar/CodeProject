package dp;

import java.util.Arrays;

public class LCS {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String s1 = "ABDFGSDTRGG";
		String s2 = "SDRFGTRGYYYT";
		
		int[][] memory = new int[s1.length()][s2.length()];
		for(int i = 0; i<memory.length ; i++ ) {
			Arrays.fill(memory[i], -1);
		}
		
		
		int lcs = lcs(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length(), memory);
		System.out.println(lcs);
		System.out.println("Time = " + (System.currentTimeMillis() - start));
	}
	
	private static int lcs(char[] s1, char[] s2, int m, int n, int[][] memory) {
		if(m==0 || n==0) {
			return 0;
		}
		if(memory[m-1][n-1] != -1) {
			return memory[m-1][n-1];
		}
		if(s1[m-1] == s2[n-1]) {
			memory[m-1][n-1] = 1 + lcs(s1, s2, m-1, n-1, memory);
			return memory[m-1][n-1];
		}else {
			memory[m-1][n-1] = Math.max(lcs(s1, s2, m-1, n, memory), lcs(s1, s2, m, n-1, memory));
			return memory[m-1][n-1];
		}
		
		//return 0;
	}
}
