package com.krushna.Java_rnd.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/description/

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is '0' or '1'.


 */
public class NumberOfIslandsL200 {
	
	public static void main(String[] args) {
		
	}
	// Left, right, up, bottom
	public static final int[][] direction = {{0,-1}, {0,1}, {-1,0},{1,0} };
	
	
    /**
     * To solve this we have to traverse the graph using BFS or DFS and then count
     * @param grid
     * @return
     */
	public static int numIslands(char[][] grid) {
		// base case
		if(grid == null || grid.length==0)
			return 0;
		
		int islandCount=0;
        
		for(int row=0; row < grid.length; ++ row) {
			for(int col=0; col< grid[row].length; ++col) {
				// Do BFS
				if(grid[row][col]=='1') {
					islandCount++;
					// we will do the BFS and mark the reachable 1 to zero
					BFS(row, col, grid);
				}
				
			}
		}
		return islandCount;
    }
	
	public static void BFS(int row, int col, char[][] grid) {
		Queue<int[]> queue= new LinkedList<int[]>();
		// we can use a visited array, or we can change the 1 to 0, to mark as visited
		grid[row][col]='0';
		queue.add(new int [] {row,col});
		while (!queue.isEmpty()) {
			int [] node=queue.poll();
			int r=node[0], c=node[1];
			// go for each direction and add the node to queue and they needs to be in bound and the value should be 1
			for(int[] dir: direction ) {
				int nr=r+dir[0];
				int nc=c+dir[1];
				if(nr>=0 && nc>=0 && nr < grid.length && nc < grid[nr].length && grid[nr][nc] == '1') {
					queue.add(new int[] {nr,nc});
					grid[nr][nc]='0';
				}
			}
			
		}
	}

   /**
    * From the time complexity point of view, it performs better its took 3ms, while BFS took 5ms 	
    * @param row
    * @param col
    * @param grid
    */
   public static void DFS(int row, int col, char[][] grid) {
	   // base case
	   if(row<0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] =='0' )
		   return;
	   
	   grid[row][col]='0';
	   
	   for(int[] dir: direction ) {
		   DFS(row+dir[0], col+dir[1], grid);
	   }
	   
   }

}
