package com.krushna.Java_rnd.neetcode.dynamic1D;

/**
 
The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.


 */
public class NthTribonacciNumber {

	public int tribonacci(int n) { //4
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		if (n == 2)
			return 1;

		// T3= T0+T1+T2;
		// Tn= Tn-1+ Tn-2+ Tn-3
		int n0 = 0;
		int n1 = 1;
		int n2 = 1;
		for (int i = 3; i <= n; ++i) {
			int ni = n0 + n1 + n2; // 2, 4
			int temp = n2;
			n2 = ni;// 2, 4
			n0 = n1;// 1, 1
			n1 = temp;// 1, 2
		}
		return n2;

	}

}
