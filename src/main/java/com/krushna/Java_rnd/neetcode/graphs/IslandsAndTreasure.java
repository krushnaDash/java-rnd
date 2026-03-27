package com.krushna.Java_rnd.neetcode.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IslandsAndTreasure {

    public void islandsAndTreasure(int[][] grid) {
        int INF=Integer.MAX_VALUE;
        multiSourceBFS(grid,INF);
        /*for(int r=0; r< grid.length; ++r){
            for(int c=0; c< grid[r].length; ++c){
                if(grid[r][c] == INF){
                    // do BFS
                    BFS(grid,r,c,INF);
                }
            }
        }*/
    }

    // do multi level BFS for all the 0 and mark the INF with level this is more optimised.
    // O(m∗n)
    public void multiSourceBFS(int[][] grid, int INF){
        int[][] visited = new int[grid.length][grid[0].length];
        int[][] directions ={{0,1},{1,0},{0,-1},{-1,0}};
        int count=0;
        Queue<int[]> queue= new LinkedList<>();

        // add all 0 to queue
        for(int r=0; r< grid.length; ++r){
            for(int c=0; c< grid[r].length; ++c){
                if(grid[r][c] == 0){
                    queue.add(new int[]{r,c});
                }
            }
        }
        while(!queue.isEmpty()){
            int size=queue.size();
           // each level
            for(int i=0; i< size; ++i){
                int[] node= queue.poll();
                int nr=node[0];
                int nc=node[1];
                if(visited[nr][nc] ==  1 || grid[nr][nc]== -1){
                    continue;
                }
                // found the land cell, update with the current count
                if(grid[nr][nc]==INF){
                    grid[nr][nc]= count;
                }
                // add all the valid node
                for(int[] dir: directions){
                    // out of bound skip
                    if( nr+dir[0] < 0 || nc+dir[1] < 0 ||
                            nr+dir[0] >= grid.length || nc+dir[1] >= grid[0].length
                            || grid[nr+dir[0]][nc+dir[1]] == -1
                    ){
                        continue;
                    }
                    queue.offer(new int[] { nr+dir[0], nc+dir[1] });
                }
                visited[nr][nc]=1;
            }
            ++count;

        }
    }
    // O((m*n)^2)
    public void BFS(int[][] grid, int r, int c, int INF){
        int[][] visited = new int[grid.length][grid[0].length];
        int[][] directions ={{0,1},{1,0},{0,-1},{-1,0}};
        int count=0;
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{r,c});
        while(!queue.isEmpty()){
            // for each level
            int size=queue.size();
            for(int i=0; i< size; ++i){
                int[] node= queue.poll();
                int nr=node[0];
                int nc=node[1];
                if(visited[nr][nc] ==  1 || grid[nr][nc]== -1){
                    continue;
                }
                // found treasure
                if(grid[nr][nc]==0){
                    grid[r][c]= count;
                    return;
                }
                // add all the valid node
                for(int[] dir: directions){
                    // out of bound skip
                    if( nr+dir[0] < 0 || nc+dir[1] < 0 ||
                            nr+dir[0] >= grid.length || nc+dir[1] >= grid[0].length
                    || grid[nr+dir[0]][nc+dir[1]] == -1
                    ){
                        continue;
                    }
                    queue.offer(new int[] { nr+dir[0], nc+dir[1] });
                }
                visited[nr][nc]=1;
            }
            ++count;
        }
    }

    public static void main(String[] args) {
        int [][] grid= {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        };

        IslandsAndTreasure it= new IslandsAndTreasure();
        it.islandsAndTreasure(grid);
        for(int[] g: grid)
        System.out.println(Arrays.toString(g));
    }

}
