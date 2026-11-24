package com.krushna.Java_rnd.neetcode.math_geometry;

import java.util.*;

/**
 * You are given a stream of points consisting of x-y coordinates on a 2-D plane. Points can be added and queried as follows:
 *
 *     Add - new points can be added to the stream into a data structure. Duplicate points are allowed and should be treated as separate points.
 *     Query - Given a single query point, count the number of ways to choose three additional points from the data structure such that the three points and the query point form a square. The square must have all sides parallel to the x-axis and y-axis, i.e. no diagonal squares are allowed. Recall that a square must have four equal sides.
 *
 * Implement the CountSquares class:
 *
 *     CountSquares() Initializes the object.
 *     void add(int[] point) Adds a new point point = [x, y].
 *     int count(int[] point) Counts the number of ways to form valid squares with point point = [x, y] as described above.
 */
public class DetectSquares {

}

class CountSquares {

    List<int[]> points;
    Map<String, Integer> pointMaps;

    public CountSquares() {
        points= new ArrayList<>(); // the performance can be improved by making this a set
        pointMaps=new HashMap<>();
    }

    public void add(int[] point) {
        points.add(point);
        String pointStr= Arrays.toString(point);
        pointMaps.put(pointStr,pointMaps.getOrDefault(pointStr,0)+1) ;
    }

    public int count(int[] point) {
        // check all the possible digonal point and count them.
        int totalSqure=0;
        for(int[] dpoint: points){
            // fount diagonal point and check both point should not be same and not used already
            if (Math.abs(dpoint[0] - point[0]) == Math.abs(dpoint[1] - point[1])
                    && dpoint[0] != point[0] ) {
                // now check the other two points
                // (x,y) and (px, py)  now look for (px,y) and (x, py)
                String point1= Arrays.toString(new int[] {point[0],dpoint[1]} );
                String point2= Arrays.toString(new int[] {dpoint[0],point[1]} );
                // check both point present
                if (pointMaps.containsKey(point1) && pointMaps.containsKey(point2)){
                    int currentSqure= pointMaps.get(point1)*pointMaps.get(point2);
                    totalSqure+=currentSqure;
                }
            }
        }
        return  totalSqure;
    }
}
