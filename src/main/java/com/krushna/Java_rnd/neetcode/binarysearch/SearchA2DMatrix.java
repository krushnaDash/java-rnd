package com.krushna.Java_rnd.neetcode.binarysearch;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 * You are given an m x n integer matrix matrix with the following two properties:

    Each row is sorted in non-decreasing order.
    The first integer of each row is greater than the last integer of the previous row.

Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

 */

public class SearchA2DMatrix {

	public static boolean searchMatrix(int[][] matrix, int target) {
		// find the row where the element might exist using a binary Search
		// then find the element in that row. bottom
		int coloumns=matrix[0].length-1;
		
		int top=0;
		int bottom=matrix.length-1;
		int row=-1;
		
		while(top <= bottom) {
			row=(top+bottom)/2;
			if(matrix[row][0] == target || matrix[row][coloumns]== target) {
				return true;
			}else if(matrix[row][0] < target && matrix[row][coloumns] < target) {
				top=row+1;
			}else if(matrix[row][0] < target &&  target < matrix[row][coloumns]) {
				// found the possible row
				break;
			}else {
				bottom=row-1;
			}
		}
		
		// no rows found if while loop break which mean top > bottom
		if(top > bottom) {
			return false;
		}
		// do the binary search on the founded row.
		
		int left=0; 
		int right= coloumns;
		
		while(left <= right) {
			int mid=(left+right)/2;
			if(matrix[row][mid] == target) {
				return true;
			}else if (target > matrix[row][mid] ) {
				left=mid+1;
			}else {
				right=mid-1;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		int[][] matrix= new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		//System.out.println(searchMatrix(matrix,3));
		int[][] matrix2= {{-8,-7,-5,-3,-3,-1,1},{2,2,2,3,3,5,7},{8,9,11,11,13,15,17},{18,18,18,20,20,20,21},{23,24,26,26,26,27,27},{28,29,29,30,32,32,34}};
		
		System.out.println(searchMatrix(matrix2,-5));
		
	}

}
