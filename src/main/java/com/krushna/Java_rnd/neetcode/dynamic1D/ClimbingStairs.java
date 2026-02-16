package com.krushna.Java_rnd.neetcode.dynamic1D;

/**
https://leetcode.com/problems/climbing-stairs/description/


You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 */
public class ClimbingStairs {

	
	public int climbStairs(int n) {
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 2;
		}
		int n1=1;
		int n2=2;
		for(int i=3; i<=n ; ++i) {
			int n3= n1+n2;
			int temp=n2;
			n2=n3;
			n1=temp;
		}
		return n2;
	}

}
