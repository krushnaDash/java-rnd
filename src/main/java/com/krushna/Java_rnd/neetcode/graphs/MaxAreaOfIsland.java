package com.krushna.Java_rnd.neetcode.graphs;

public class MaxAreaOfIsland {

	public int maxAreaOfIsland(int[][] grid) {
		int maxArea=0;
		for(int i=0; i< grid.length; ++i) {
			for(int j=0; j < grid[i].length; ++j) {
				if(grid[i][j] == 1) {
					// do DFS to find the area
					maxArea=Math.max(maxArea, dfsArea(grid, i, j));
				}
			}
		}
		
		return maxArea;
	}
	
	public int dfsArea(int[][] grid, int r, int c) {
		int sum=0;
		if(r<0 || c<0 || r>= grid.length || c>= grid[0].length || grid[r][c] ==0) {
			return sum;
		}
		
		grid[r][c]=0;
		sum=1;
		sum=sum+dfsArea(grid, r-1,c);
		sum=sum+dfsArea(grid, r+1,c);
		sum=sum+dfsArea(grid, r,c+1);
		sum=sum+dfsArea(grid, r,c-1);
		return sum;
		
	}

}
