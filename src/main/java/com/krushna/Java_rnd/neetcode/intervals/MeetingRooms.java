package com.krushna.Java_rnd.neetcode.intervals;

import java.util.Collections;
import java.util.List;

/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their schedule without any conflicts.
 * Note: (0,8),(8,10) is not considered a conflict at 8
 * Example 1:
 * Input: intervals = [(0,30),(5,10),(15,20)]
 *
 * Output: false
 */
public class MeetingRooms {

    public boolean canAttendMeetings(List<Interval> intervals) {
        // base case
        if(intervals.size() <=1){
            return  true;
        }
        // sort the interval by their start time
        Collections.sort(intervals, (Interval a, Interval b) -> a.start- b.start);
        // ierate over them, if there is a overlap return false
        int prevIntervalEndValue= intervals.get(0).end;
        for( int i=1; i < intervals.size(); ++i){
            Interval next=intervals.get(i);
            // check for overlap
            if(next.start < prevIntervalEndValue){
                return  false;
            }
            prevIntervalEndValue=next.end;
        }
        return  true;
    }

}
