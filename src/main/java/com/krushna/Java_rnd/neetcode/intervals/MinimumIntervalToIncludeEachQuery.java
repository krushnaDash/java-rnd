package com.krushna.Java_rnd.neetcode.intervals;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        return  findMin(intervals, queries);
    }

    public int[] usingMinHeap(int[][] intervals, int[] queries){
        // sort the both
        int[] copyQuries= Arrays.copyOf(queries, queries.length);
        Arrays.sort(copyQuries);
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0]-b[0]);
        // use a Map to store the value
        Map<Integer, Integer> qMap= new HashMap<>();
        // sort by size and then end value
        PriorityQueue<int[]> minHeap=
                new PriorityQueue<>( (int[] a, int [] b) ->  a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

        // iterate ove query
        int start=0;
        for(int q: copyQuries){
            while(start < intervals.length && intervals[start][0] <=q ){
                // add to min Heap and incriment start
                int [] interval=intervals[start];
                int size=interval[1]-interval[0]+1;
                minHeap.offer(new int[] {size, interval[1]} );
                ++start;
            }
            // check the minHeap peak end is >= Q
            while(!minHeap.isEmpty() && minHeap.peek()[1]< q){
                minHeap.poll(); // remove element
            }
            if(!minHeap.isEmpty())
                qMap.put(q, minHeap.peek()[0]);
        }
        int[] min= new int[queries.length];
        for(int i =0; i < queries.length; ++i){
            min[i]=qMap.getOrDefault(queries[i], -1);
        }
        return  min;

    }

    // this is also not full optimised, what else.
    public int[] findMinv3 (int[][] intervals, int[] queries) {
        int[] copyQuries= Arrays.copyOf(queries, queries.length);
        Arrays.sort(copyQuries);
        // Lets have a map with key all posssible number, value as min value
        Map<Integer, Integer> qMap= new HashMap<>();
        for(int[] interval : intervals){
            int start=interval[0];
            int end=interval[1];
            // now go from interval start
            for(int i: copyQuries){ // do i need to check all the num, why not check only Query
               // i is in the interval
                if(i >= start && i <= end) {
                    qMap.put(i, Math.min(qMap.getOrDefault(i, Integer.MAX_VALUE), end - start + 1));
                }else if ( i > end){
                    break;
                }
                // if i >end no need to consider any other element from this interva
            }
        }
        // iterate for each Q and find min from the map
        int[] min= new int[queries.length];
        for(int i =0; i < queries.length; ++i){
            min[i]=qMap.getOrDefault(queries[i], -1);
        }
        return  min;
    }


    // memory is optmised, can we optimise the time complxity
    public int[] findMinv2 (int[][] intervals, int[] queries) {
        // use query set and store only those number
        Set<Integer> qSet= new HashSet<>();
        for(int q:queries){
            qSet.add(q);
        }

        // Lets have a map with key all posssible number, value as min value
        Map<Integer, Integer> qMap= new HashMap<>();
        for(int[] interval : intervals){
            int start=interval[0];
            int end=interval[1];
            // now go from interval start
            for(int i=start; i <= end; ++ i){ // do i need to check all the num, why not check only Query
                if(qSet.contains(i)) {
                    qMap.put(i, Math.min(qMap.getOrDefault(i, Integer.MAX_VALUE), end - start + 1));
                }
            }
        }
        // iterate for each Q and find min from the map
        int[] min= new int[queries.length];
        for(int i =0; i < queries.length; ++i){
            min[i]=qMap.getOrDefault(queries[i], -1);
        }
        return  min;
    }

    // using bruteforce
    public int[] findMin (int[][] intervals, int[] queries) {
        // Lets have a map with key all posssible number, value as min value
        Map<Integer, Integer> qMap= new HashMap<>();
        for(int[] interval : intervals){
            int start=interval[0];
            int end=interval[1];
            for(int i=start; i <= end; ++ i){ // do i need to check all the num, why not check only Query
                qMap.put(i, Math.min(qMap.getOrDefault(i, Integer.MAX_VALUE), end-start+1));
            }
        }
        // iterate for each Q and find min from the map
        int[] min= new int[queries.length];
        for(int i =0; i < queries.length; ++i){
            min[i]=qMap.getOrDefault(queries[i], -1);
        }
        return  min;
    }

}
