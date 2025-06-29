package com.krushna.Java_rnd.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/max-area-of-island/description/
 * 
 * 
 * You are given an m x n binary matrix grid. An island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * The area of an island is the number of cells with a value 1 in the island.
 * 
 * Return the maximum area of an island in grid. If there is no island, return
 * 0.
 */

public class MaxAreaOfIslandL695 {

	public int maxAreaOfIsland(int[][] grid) {
		int maxArea=0;
		for(int i=0; i < grid.length; ++ i) {
			for(int j=0; j< grid[i].length; ++j) {
				// Found land calculate the area
				if(grid[i][j] ==1) {
					int currntArea=areaWithDFS(grid, i,j);
					if(currntArea > maxArea)
						maxArea=currntArea;
				}
			}
		}
		return maxArea;
	}
	
	/**
	 * Let do DFS and replace the 1 to zero to mark as visited
	 * @return
	 */
	public int areaWithDFS(int[][] grid, int row, int cols ) {
		int count=0;
		// base case
		if(row <0 || cols <0 || row>= grid.length || cols >= grid[row].length || grid[row][cols] ==0) {
			return count;
		}else {
			// increment the count
			count=1;
			grid[row][cols]=0;
			// for each direction
			count=count+areaWithDFS(grid, row-1, cols); 
			count=count+areaWithDFS(grid, row+1, cols );
			count=count+areaWithDFS(grid, row, cols-1 );
			count=count+areaWithDFS(grid, row, cols+1);
			return count;
		}
	}
	
	/**
	 * Let do DFS and replace the 1 to zero to mark as visited
	 * @return
	 */
	public int areaWithBFS(int[][] grid, int row, int col ) {
		Queue<int[]> nodes= new LinkedList<int[]>();
		nodes.add(new int[] {row,col});
		int count=0;
		while(!nodes.isEmpty()) {
			int[] node=nodes.poll();
			// check for valid node
			int nrow=node[0];
			int ncol=node[1];
			if(nrow <0 || ncol <0 || nrow>= grid.length || ncol >= grid[row].length || grid[nrow][ncol] ==0) {
				continue;
			}
			
			grid[nrow][ncol]=0;
			count++;
			// add for all direction
			nodes.add(new int[] {nrow-1, ncol});
			nodes.add(new int[] {nrow+1, ncol});
			nodes.add(new int[] {nrow, ncol-1});
			nodes.add(new int[] {nrow, ncol+1});
			
		}
		return count;
		
	}

}
