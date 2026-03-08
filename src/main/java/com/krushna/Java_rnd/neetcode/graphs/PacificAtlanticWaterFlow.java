package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and
 * Atlantic Ocean. The Pacific Ocean touches the island's left and top edges,
 * and the Atlantic Ocean touches the island's right and bottom edges.
 * 
 * The island is partitioned into a grid of square cells. You are given an m x n
 * integer matrix heights where heights[r][c] represents the height above sea
 * level of the cell at coordinate (r, c).
 * 
 * The island receives a lot of rain, and the rain water can flow to neighboring
 * cells directly north, south, east, and west if the neighboring cell's height
 * is less than or equal to the current cell's height. Water can flow from any
 * cell adjacent to an ocean into the ocean.
 * 
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci]
 * denotes that rain water can flow from cell (ri, ci) to both the Pacific and
 * Atlantic oceans.
 * 
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 */
public class PacificAtlanticWaterFlow {
	
	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> res= new ArrayList<List<Integer>>();
		// For the pacific ocean the first cell of top and left can reach
		// Same  Bottom, Right for Atlantic ocean
		// The idea here is we will check which all cell also eligible for each cell of top and left for Pacific Ocean
		// Same for Bottom Right for Atlantic Ocean
		// Lets have two set keep identified cell. we can use an Boolean array also here, as this is bounded
		int ROWS=heights.length;
		int COLS=heights[0].length;
		
		boolean [][] po= new boolean[ROWS][COLS];
		boolean [][] ao= new boolean[ROWS][COLS];
		
		// top left
		
		for(int c=0; c<COLS; ++c) {
			// TOP
			checkWithDFS(0, c, heights, po, heights[0][c]);
			//Bottom
			checkWithDFS(ROWS-1, c, heights, ao, heights[ROWS-1][c]);
			
		}
		
		for(int r=0; r<ROWS; ++r) {
			// LEFT
			checkWithDFS(r, 0, heights, po, heights[r][0]);
			//RIGHT
			checkWithDFS(r, COLS-1, heights, ao, heights[r][COLS-1]);
		}
		// check both sets and both true add them to list
		for(int i=0; i < ROWS; ++i) {
			for(int j=0; j<COLS; ++j) {
				if(po[i][j] && ao[i][j]) {
				res.add(List.of(i,j));	
				}
			}
		}
		return res;
		
    }
	
	public void checkWithDFS(int r, int c, int[][] heights, boolean[][] identified, int prevValue) {
		// base case to stop DFS out of bound and value is < then or already visited (identified)
		if(r<0||c<0||r>=heights.length||c>=heights[0].length || heights[r][c] < prevValue || identified[r][c]) {
			return;
		}
		identified[r][c]=true;
		// now check for each direction
		checkWithDFS(r+1, c, heights, identified, heights[r][c]);
		checkWithDFS(r-1, c, heights, identified, heights[r][c]);
		checkWithDFS(r, c+1, heights, identified, heights[r][c]);
		checkWithDFS(r, c-1, heights, identified, heights[r][c]);
		
	}
	
	public static void main(String[] args) {
		int [][] heights= new int[][] {{1,2,3},{8,9,4},{7,6,5}};
		PacificAtlanticWaterFlow po= new PacificAtlanticWaterFlow();
		System.out.println(po.pacificAtlantic(heights));
	}

}
