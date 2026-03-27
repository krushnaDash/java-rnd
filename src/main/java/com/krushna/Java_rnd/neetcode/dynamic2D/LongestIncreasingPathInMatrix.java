package com.krushna.Java_rnd.neetcode.dynamic2D;

import java.util.Arrays;

public class LongestIncreasingPathInMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        Integer[][] dp = new Integer[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                count = Math.max(count, DFSwithMemo(matrix, i, j, dp));
            }
        }
        return count;
    }

    public int DFSwithMemo(int[][] matrix, int i, int j, Integer[][] dp){
        int [][] dir= {{0,1}, {1,0}, {0,-1}, {-1,0}};
        if(dp[i][j] !=null ){
            return dp[i][j];
        }
        int count=1;
        // check for all 4 direction and call DFS
        int value=0;
        for(int d[] : dir){
            int ni= i+d[0];
            int nj=j+d[1];
            if( ni < matrix.length && nj < matrix[0].length && ni>=0 && nj >=0 &&
            matrix[ni][nj] > matrix[i][j]) {
                value=Math.max(value,DFSwithMemo(matrix,ni,nj, dp));
            }
        }
        count=count+value;
        dp[i][j]=count;
        return  count;
    }

    public static void main(String[] args) {
        int[][] matrix={{5,5,3},{2,3,6},{1,1,1}};
        LongestIncreasingPathInMatrix lp = new LongestIncreasingPathInMatrix();
        System.out.println(lp.longestIncreasingPath(matrix));
    }

}
