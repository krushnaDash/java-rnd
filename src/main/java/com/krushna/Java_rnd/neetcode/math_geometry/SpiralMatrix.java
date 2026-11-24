package com.krushna.Java_rnd.neetcode.math_geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res= new ArrayList<>();
        int top=0, left=0;
        int right=matrix[0].length-1;
        int bottom=matrix.length-1;
        while (left <= right && top <= bottom){
            // Top left to top right
            for(int i =left; i<=right; ++i){
                res.add(matrix[top][i]);
            }
            ++top;
            // Top right to bottom Right
            for(int i =top; i<= bottom; ++i){
                res.add(matrix[i][right]);
            }
            --right;
            // Bottom right to bottom left (only if there's a row remaining)
            if(top <= bottom) {
                for (int i = right; i >= left; --i) {
                    res.add(matrix[bottom][i]);
                }
                --bottom;
            }
            // Bottom Left to Top Left only if there is column remaining
            if(left<=right) {
                for (int i = bottom; i >= top; --i) {
                    res.add(matrix[i][left]);
                }
                ++left;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int [][] matrix= {{1,2,3},{4,5,6},{7,8,9}};
        SpiralMatrix sm= new SpiralMatrix();
        System.out.println(sm.spiralOrder(matrix));
    }
}
