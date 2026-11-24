package com.krushna.Java_rnd.neetcode.intervals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of rooms required to schedule all meetings without any conflicts.
 * Note: (0,8),(8,10) is NOT considered a conflict at 8.
 * Example 1:
 * Input: intervals = [(0,40),(5,10),(15,20)]
 *
 * Output: 2
 * Explanation: day1: (0,40) day2: (5,10),(15,20)
 * Example 2:
 * Input: intervals = [(4,9)]
 *
 * Output: 1
 *
 */
public class MeetingRoomsII {

    public int minMeetingRooms(List<Interval> intervals) {
        // base case
        if(intervals.size() <=1){
            return  1;
        }
        if(intervals.isEmpty()){
            return  0;
        }
        //return  usingMinHeap(intervals);
        return  usingTwoPointer(intervals);
    }
    // at any point we need to find out how many meeting needs to run, and the max value is result
    public int usingTwoPointer(List<Interval> intervals){
        // lets have the start and end time array
        int[] startTime= new int[intervals.size()];
        int[] endTime= new int[intervals.size()];
        for(int i=0; i < intervals.size(); ++i){
            startTime[i]= intervals.get(i).start;
            endTime[i]=intervals.get(i).end;
        }
        // srot both the array
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        // now iterate over start and end time using two pointer
        int i=0, j=0;
        int count=0;
        int maxRoom=0;
        while (i < startTime.length && j < endTime.length){
            if(startTime[i] < endTime[j]){
                // one more meeting to start
                ++i;
                ++count;
                maxRoom=Math.max(count, maxRoom);
            }else{
                // one meeting needs to end
                ++j;
                --count;
            }
        }
        return maxRoom;
    }


    public int usingMinHeap(List<Interval> intervals){
        // sort them based on their star time
        Collections.sort(intervals, (Interval a, Interval b) -> a.start-b.start);
        // instead array lets use a MinHeap for Room end time, pick a room based on the min end time
        // is the min end time room overlap. we need to have a new room
        PriorityQueue<Integer> rooms= new PriorityQueue<>();
        // add the first meeting room with its end time
        rooms.offer(intervals.get(0).end);
        for(int i=1; i < intervals.size(); ++i){
            Interval nextMeeting= intervals.get(i);
            // check if we need new room, based on overlap
            if( nextMeeting.start < rooms.peek()){
                // overlap create a new room and add
                rooms.offer(nextMeeting.end);
            }else{
                // update end time for the min one
                rooms.poll();
                rooms.offer(nextMeeting.end);
            }
        }
        return rooms.size();
    }

    public static void main(String[] args) {

    }

}
