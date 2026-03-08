package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.
 *
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 */
public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        boolean[][] visited = new boolean[ROWS][COLS];
        // we can do a BFS with minHeap, similar to MST
        //minimum spanning tree Prim's algo similar to Dijkastra
        // but here the edge weight will be max(currentNode, previousNode)
        Queue<int[]> minHeap = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
        // 0 weight, r, c
        // Start BFS
        minHeap.add(new int[]{grid[0][0], 0, 0});
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            // if we reach to end return
            int[] node = minHeap.poll();
            int r = node[1];
            int c = node[2];

            // check is it the end node (r-1, c-1)
            if (r == ROWS - 1 && c == COLS - 1)
                return node[0];

            if (visited[r][c])
                continue;
            visited[r][c] = true;
            // add node from each dir to the min heap if they are valid.
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                // out of bound or visited
                if (nr < 0 || nc < 0 || nr >= ROWS || nc >= ROWS || visited[nr][nc]) {
                    continue;
                }
                // weight, row, col
                minHeap.offer(new int[]{Math.max(grid[nr][nc], node[0]), nr, nc});
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        int [][] grid= {{0,2},{1,3}};
        SwimInRisingWater sr= new SwimInRisingWater();
        System.out.println(sr.swimInWater(grid));
    }
}
