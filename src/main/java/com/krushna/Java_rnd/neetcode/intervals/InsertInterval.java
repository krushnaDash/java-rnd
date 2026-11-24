package com.krushna.Java_rnd.neetcode.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res= new ArrayList<>();
        // we will update the newInterval with the right interval and insert it once we find a position.
        // here we check each interval if its before just add the current one
        // if the new one is after add both
        // else merge the current one with the new one and continue

        for(int[] interval : intervals){
            // the current one before the new one
            if(newInterval==null || interval[1] < newInterval[0]){
                res.add(interval);// add the current interval
            // check the current one after the new one then add both
            }else if (interval[0] > newInterval[1]){
                res.add(newInterval);
                res.add(interval);
                // make newInterval null so that we will not add it again
                newInterval=null;
            }else{ // overlap do the merge
                newInterval[0]= Math.min(newInterval[0], interval[0]);
                newInterval[1]= Math.max(newInterval[1], interval[1]);
                // skip the current interval as its got merged
            }

        }
        // the newInterval did not got inserted at any poistion as it has to go last, add it at the end
        if(newInterval !=null){
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][2]);
    }


    public static void main(String[] args) {
        InsertInterval i=  new InsertInterval();
        int[][] ivo= new int[][]{{2,4},{7,8}};
        i.insert(ivo, new int[] {5,7});

        int[][] iv= new int[][]{{1,2},{3,5},{6,7},{8,10}, {12,16}};

        i.insert(iv, new int[] {4,8});

        int[][] iv2= new int[][]{{1,3},{6,9}};
        int[][] iv3= new int[][]{{1,5}};

        i.insert(iv3, new int[] {2,7});

    }

}
