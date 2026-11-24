package com.krushna.Java_rnd.neetcode.math_geometry;

import java.util.Arrays;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *You must do it in place.
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int rowZero=-1; // to store the extra one more position, that the postion 0 for the row array


        // use the first rows and cols to for the storage
           for(int i=0; i < matrix.length; ++i){
               for(int j=0; j < matrix[i].length; ++j){
                   if(matrix[i][j] ==0){
                       if(i==0){
                           rowZero=0;
                       }else {
                           matrix[i][0] = 0; // mark the row to 0
                       }
                       // mark the cols also to be zero
                       matrix[0][j]=0;
                   }
               }
           }
           // now process the first rows and cols and do inplace replacement

        // process the first rows, that cols array to mark the cols to 0
        // skip the first cols and rows , we need to do them last

        for(int i=1; i < matrix[0].length; ++i){
            if(matrix[0][i]==0){
                for(int j =0; j < matrix.length; ++j){
                    matrix[j][i]=0;
                }
            }
        }
        // now process the firs cols for the rows, start from 1 and use the rowZero also
        // here if rowZero is 0, the m[0][0] become 0, so we will start from 1
        for(int i=1; i < matrix.length; ++i){
            if(matrix[i][0]==0){
                for(int j =0; j < matrix[0].length; ++j){
                    matrix[i][j]=0;
                }
            }
        }

        // now process the first position 0
        if(matrix[0][0]==0){
            // mark the fisrt cols as 0
            for(int j =0; j < matrix.length; ++j){
                matrix[j][0]=0;
            }
        }
        // process the rowZero
        if(rowZero==0){
            Arrays.fill(matrix[0],0);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        SetMatrixZeroes smz= new SetMatrixZeroes();
        smz.setZeroes(matrix);
    }
}
