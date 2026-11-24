package com.krushna.Java_rnd.neetcode.bitManipulation;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 */
public class CountingBits {

    public int[] countBits(int n) {
        int [] dp= new int[n+1];
         dp[0]=0;
         int currentOffset=1;
         for(int i=1; i <=n; ++i){
             if(i >=  currentOffset *2) {
                 currentOffset=currentOffset*2;
             }
             dp[i]=1+dp[i-currentOffset];
         }
         return dp;
    }

    public static void main(String[] args) {
        CountingBits cb = new CountingBits();
        System.out.println(Arrays.toString(cb.countBits(8)));
    }

}
