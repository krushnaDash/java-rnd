package com.krushna.Java_rnd.neetcode.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 https://leetcode.com/problems/rotting-oranges/description/
 
 You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 */
public class RottingOranges {

	public int orangesRotting(int[][] grid) {
		// this should be solve with BFS, in each pass collect the rotten position and store them
		int minTime=-1;
		
		Queue<int[]> positionQueue= new LinkedList<int[]>();
		int[][] direction= {{0,1},{1,0},{0, -1}, {-1,0}};
		
		for(int r=0; r< grid.length; ++r) {
			for(int c=0; c< grid[r].length; ++c) {
				if(grid[r][c]==2) {
					positionQueue.add(new int [] {r,c});
				}
			}
		}
		// take the size for each pass
		while(!positionQueue.isEmpty()) {
			int size=positionQueue.size();
			minTime++;
			for(int i=0; i < size; ++i) {
				int[] pos= positionQueue.poll();
				// check for all direction
				int r=pos[0];
				int c=pos[1];
				for(int[] dir: direction) {
					if (r + dir[0] >= 0 && r + dir[0] < grid.length && c + dir[1] >= 0 && c + dir[1] < grid[0].length
							&& grid[r + dir[0]][c + dir[1]] == 1) {
						
						grid[r + dir[0]][c + dir[1]] = 2;
						positionQueue.offer(new int[] { r + dir[0], c + dir[1] });
					}
				}
				
			}
			
		}
		// if queue is empty check there is no 1, fresh orange, if yes return min time, else -1
		for(int r=0; r< grid.length; ++r) {
			for(int c=0; c< grid[r].length; ++c) {
				if(grid[r][c]==1) {
					return -1;
				}
			}
		}
		
		// since we started from -1
		return minTime==-1? 0: minTime;
	}

}
