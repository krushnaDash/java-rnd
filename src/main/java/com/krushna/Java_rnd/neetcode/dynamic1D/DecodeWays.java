package com.krushna.Java_rnd.neetcode.dynamic1D;

import java.util.Arrays;

/**
 * ou have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 *
 * "1" -> 'A'
 *
 * "2" -> 'B'
 *
 * ...
 *
 * "25" -> 'Y'
 *
 * "26" -> 'Z'
 *
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 *
 * For example, "11106" can be decoded into:
 *
 *     "AAJF" with the grouping (1, 1, 10, 6)
 *     "KJF" with the grouping (11, 10, 6)
 *     The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 *
 * Note: there may be strings that are impossible to decode.
 *
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 *
 * Output: 2
 *
 * Explanation:
 *
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 */
public class DecodeWays {



    public int numDecodings(String s) {
        // base case
        if( s==null || s.isEmpty() || s.startsWith("0")){
            return 0;
        }
        int n1=1; // the value for remaining i+2 char dp[i+2]
        int n2=1; // the value for remaining i+1 char dp[i+1]
        // dp[i] to calculate the current value
        // start from reverse
        int n=s.length();
        for(int i=n-1; i>=0; --i){
            int current=0; // current position decode
            if(s.charAt(i) !='0'){
                // single digit decode
                current=n2; // same as the de-code of n2, no change
            }
            // check if it can be combine
            if(i+1 < n && checkCombine(s.charAt(i), s.charAt(i+1)) ){
                current= current+n1; // we can add the num ways decode for n1 ( i+2) char
            }
            // shift the window
            n1=n2;
            n2=current;
        }
        return  n2;
    }

    public int numDecodingsv1(String s) {
        char[] nums=s.toCharArray();
        int[] dp= new int[s.length()+1];
        Arrays.fill(dp, -1);
        //return DFS(nums,0);
        return DFSWithDP(nums,0, dp);
    }



    // DFS with DP to optimise, if i is already visited, return the calulated value
    public int DFSWithDP(char[] nums, int i, int[] dp){
        // base case
        if(i>= nums.length){ // reach end
            return 1;
        }
        if(dp[i] !=-1){ // not -1 means it is caluclated with some value. i is visited
            return dp[i];
        }
        if(nums[i] == '0'){
            return 0;
        }
        int res= DFSWithDP(nums, i+1,dp);
        // check if the n and n+1 can be combine ??
        if(i<nums.length-1 && checkCombine(nums[i], nums[i+1])  ){ // we have enough char
            res+= DFSWithDP(nums, i+2,dp);
        }
        dp[i]=res; // store the result
        return  res;
    }

    public boolean checkCombine(char c1, char c2){
        int n1= Character.getNumericValue(c1);
        int n2= Character.getNumericValue(c2);
        if( (n1==1 || (n1==2 && n2<=6)))
            return true;
        else
            return false;
    }

    // DFS with back tracking for each combination
    public int DFS(char[] nums, int i){
        // base case
        if(i>= nums.length){ // reach end
            return 1;
        }
        if(nums[i] == '0'){
            return 0;
        }
        int res= DFS(nums, i+1);
        // check if the n and n+1 can be combine ??
        if(i<nums.length-1 && checkCombine(nums[i], nums[i+1])  ){ // we have enough char
            res+= DFS(nums, i+2);
        }
        return  res;
    }


    public static void main(String[] args) {
        DecodeWays dw= new DecodeWays();
        System.out.println( dw.numDecodings("101"));
        System.out.println( dw.numDecodings("226"));
        System.out.println( dw.numDecodings("2101"));

    }

}
