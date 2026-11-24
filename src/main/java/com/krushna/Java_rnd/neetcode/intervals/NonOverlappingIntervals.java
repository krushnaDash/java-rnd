package com.krushna.Java_rnd.neetcode.intervals;

import java.util.Arrays;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // lets sort the interval first with their start value
        Arrays.sort(intervals, (int[] a, int [] b) -> a[0]-b[0]);
        // iterate the array and find the overlapping inteval and then remove one which end is max
        // this beacuase, the interval which end is more, it has more chance to overlap with other
        if(intervals.length <=1){
            return  0;
        }
        int count=0;
        int prevIntervalEndValue=intervals[0][1];
        for(int i=1; i < intervals.length; ++i){
            int [] nextInerval= intervals[i];
            // check for overlap
            if(nextInerval[0] < prevIntervalEndValue){
                count++;
                // check which one to remove, remove interval which end value is max
                // which means keep the interval which end value is min
                prevIntervalEndValue= Math.min(prevIntervalEndValue, nextInerval[1]);
            }else {
                prevIntervalEndValue=nextInerval[1];
            }
        }
        return  count;
    }
}
