package com.krushna.Java_rnd.neetcode.math_geometry;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        // we have rotate the image in place
        int left=0,right=matrix.length-1;
        while(left < right){
            // since its a squr matrix
            int top=left, bootom=right;
            for(int i =0; i< (right-left); ++i ){
                // do the shift
                int temp= matrix[top][left+i];
                matrix[top][left+i]=matrix[bootom-i][left];
                matrix[bootom-i][left]=matrix[bootom][right-i];
                matrix[bootom][right-i]=matrix[top+i][right];
                matrix[top+i][right]=temp;
            }
            ++left; --right;
        }
    }

    public static void main(String[] args) {
        int[][] matrix= {{1,2,3}, {4,5,6},{7,8,9}};
        RotateImage ri= new RotateImage();
        ri.rotate(matrix);
    }
}
