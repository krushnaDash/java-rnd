package com.krushna.Java_rnd.neetcode.dynamic2D;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 *     Insert a character
 *     Delete a character
 *     Replace a character
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        Integer[][] dp= new Integer[word1.length()][word2.length()] ;
        return  DFSMemo(word1,word2,0,0,dp);
    }
    public int dpBottomUp(String w1, String w2){
        int[][] dp = new int[w1.length()+1][w2.length()+1];
        // fill the last and last column
        // last will start from the 0 to max length
        for(int i=w1.length(); i>=0; --i){
            dp[i][w2.length()]=w1.length()-i; // 0 to w1.length
            // last coloumn will be like min ways to form "" string from 0 to n char, which wlll be like 0 to n
        }
        // last row
        for(int i=w2.length(); i>=0; --i){
            dp[w1.length()][i]=w2.length()-i; // 0 to w1.length
        }
        // run the bottom up approach
        for(int i=w1.length()-1; i>=0; --i){
            for(int j=w2.length()-1; j>=0; --j){
                if(w1.charAt(i)== w2.charAt(j)){
                    dp[i][j]=dp[i+1][j+1];
                }else{
                    // chose 1+ min of all Replace, Insert, or Delete
                    dp[i][j]=1+ Math.min(dp[i+1][j+1], Math.min(dp[i][j+1], dp[i+1][j]));
                }
            }
        }
        return  dp[0][0];
    }

    // DFS with memo
    public int DFSMemo(String word1, String word2, int i, int j, Integer [][] dp){
        // base case
        int res=0;
        if(i>= word1.length()){
            // no char left for word 1, simple insert the remain char
            return word2.length() - j;
        }
        if(j>= word2.length()){
            return  word1.length()-i;
        }
        if(dp[i][j] !=null){
            return dp[i][j];
        }
        // if the char match just incriment i and j with no +1 to response
        if(word1.charAt(i) == word2.charAt(j)){
            res=DFSMemo(word1,word2,i+1, j+1,dp);
        }else{
            int replace=DFSMemo(word1,word2,i+1, j+1,dp);
            int delete=DFSMemo(word1,word2,i+1, j,dp);
            int insert=DFSMemo(word1,word2,i, j+1,dp);
            res= 1+ Math.min(insert, Math.min(replace,delete));
        }
        dp[i][j]=res;
        return  res;
    }

    public static void main(String[] args) {
        EditDistance ed= new EditDistance();
        System.out.println(ed.minDistance("horse","ros"));
    }

}
