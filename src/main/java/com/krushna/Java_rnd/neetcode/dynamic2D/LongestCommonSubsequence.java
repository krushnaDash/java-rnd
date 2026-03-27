package com.krushna.Java_rnd.neetcode.dynamic2D;

import java.util.Arrays;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 *     For example, "ace" is a subsequence of "abcde".
 *
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        // this can solve with DP lets take mXn matrix
        int rows=text1.length()+1;
        int cols=text2.length()+1;
        int[][] DP= new int[rows][cols];
        // fill the last rows and last cols with 0, in int defult value is 0
        // do the bootom up approach, skip the last rows and cols
        for(int i=rows-2; i>=0; --i){
            for(int j=cols-2; j>=0; --j){
                // if char match
                if(text1.charAt(i)== text2.charAt(j)) {
                    DP[i][j] = 1 + DP[i + 1][j + 1];
                }else{
                    DP[i][j] = Math.max(DP[i+1][j], DP[i][j+1]);
                }
            }
        }
        return DP[0][0];
    }

}
