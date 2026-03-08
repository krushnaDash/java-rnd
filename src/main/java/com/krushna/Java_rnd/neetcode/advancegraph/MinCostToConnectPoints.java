package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.*;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 */
public class MinCostToConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        // lets create the adjacency List, we can use ArrayList this time
        List<List<int[]>> adjList= new ArrayList<>();
        for(int i=0; i < points.length; ++i){
            // create with their distance
            List<int[]> pointList= new ArrayList<>();
            for(int j=0; j< points.length; ++j){
                if(i==j) continue; // skip the same points
                int[] point1= {points[i][0], points[i][1]};
                int [] point2= {points[j][0], points[j][1]};
                int dist=  Math.abs(point1[0]-point2[0]) + Math.abs(point1[1]-point2[1]);
                // add it to the list
                pointList.add(new int [] {dist,j});
            }
            adjList.add(pointList);
        }
        return BFSWithMST(adjList);
    }
    // prim's algo with MST
    public int BFSWithMST(List<List<int[]>> adjList){
        int cost=0;
        Queue<int[]> minHeap= new PriorityQueue<>( (int[]a, int [] b) -> a[0]-b[0] );
        minHeap.add(new int[] {0,0}); // add the 0th node
        Set<Integer> visited= new HashSet<>();
        // we will run till all the points are visited
        while (visited.size() < adjList.size() && !minHeap.isEmpty()){
            // pop the fisrt one from the minHeap
            int [] point= minHeap.poll();
            if(visited.contains(point[1])){
                continue;
            }
            visited.add(point[1]); // index 1 is point and 0 is weight
            cost= cost+point[0]; // add the cost

            for(int[] np : adjList.get(point[1])){
                if(visited.contains(np[1]))
                    continue;
                minHeap.offer(np);
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int[][] points= {{0,0},{2,2},{3,10},{5,2},{7,0}};
        MinCostToConnectPoints mp= new MinCostToConnectPoints();
        System.out.println(mp.minCostConnectPoints(points));

    }
}
