package com.krushna.Java_rnd.neetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
       return  mergeWithSoring((intervals));

    }
    // not fixed yet, todo make code work
    public int[][] mergeNoSoring(int[][] intervals){
        List<int[]> res= new ArrayList<>();
        // iterate over all interval and merge them if overlap
        if(intervals.length >=2) {
            int[] i1=intervals[0]; // take the first element as new one
            for (int i = 1; i < intervals.length; ++i) {
                int[] i2 = intervals[i];
                // check for over lapping
                if (i1[0] == i2[0] || i1[1] == i2[1]
                        || (i1[0] < i2[1] && i1[0] > i2[0]) )
                 {
                    // merge
                    i1[0]=Math.min(i1[0], i2[0]);
                    i1[1]=Math.max(i1[1], i2[1]);
                } else {
                    // add the i1 to response
                    res.add(i1);
                    i1=i2;// update the i1 as previous element
                }
            }
            // add the i1 which will be last element if merge or not
            res.add(i1);
        }else{
            return  intervals;
        }
        return res.toArray(new int[res.size()][2]);
    }

    public int[][] mergeWithSoring(int[][] intervals){
        // sort the interval based on their start time
        Arrays.sort(intervals, (int[] a, int [] b) -> a[0]-b[0]);
        List<int[]> res= new ArrayList<>();
        // iterate over all interval and merge them if overlap
        if(intervals.length >=2) {
            int[] i1=intervals[0]; // take the first element as new one
            for (int i = 1; i < intervals.length; ++i) {
                int[] i2 = intervals[i];
                // check for over lapping
                if (i1[0] == i2[0] || i1[1] >= i2[0]) {
                    // merge
                    i1[0]=Math.min(i1[0], i2[0]);
                    i1[1]=Math.max(i1[1], i2[1]);
                } else {
                    // add the i1 to response
                    res.add(i1);
                    i1=i2;// update the i1 as previous element
                }
            }
            // add the i1 which will be last element if merge or not
            res.add(i1);
        }else{
            return  intervals;
        }
        return res.toArray(new int[res.size()][2]);
    }

}
