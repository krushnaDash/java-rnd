package com.krushna.Java_rnd.neetcode.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
	Map<String, List<TimeValue>> timeMap;
	
	
    public TimeBasedKeyValueStore() {
    	timeMap= new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
    	timeMap.computeIfAbsent(key,  (ignoreKey) -> new ArrayList<>()).add(new TimeValue(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        List<TimeValue> timeValues=timeMap.get(key);
        
        // base case
        if(timeValues == null || timeValues.isEmpty()){
          return "";
        }
        
        // binary search
        int left=0;
        int right=timeValues.size()-1;
        TimeValue timeValue=null;
        TimeValue closestTimeValue= null;
        while(left <= right) {
        	int mid=(left+right)/2;
        	timeValue= timeValues.get(mid);
        	if(timeValue.time==timestamp) {
        		return timeValue.value;
        	}else if (timestamp > timeValue.time) {
        		left= mid+1;
        		closestTimeValue= timeValue;
        	}else {
        		right=mid-1;
        	}
        }
        
        if(timeValue.time < timestamp) {
        	return timeValue.value;
        }else if(closestTimeValue != null) {
        	return closestTimeValue.value;
        }else {
        	return "";
        }
        
        
    }
    
    
    static record TimeValue(String value, int time) {}
    
    public static void main(String[] args) {
    	TimeBasedKeyValueStore timeBasedKeyValueStore= new TimeBasedKeyValueStore();
    	
    	timeBasedKeyValueStore.set("foo","bar", 1);
    	
    	System.out.println(timeBasedKeyValueStore.get("foo", 1));
    	System.out.println(timeBasedKeyValueStore.get("foo", 3));
    	
    	timeBasedKeyValueStore.set("foo","bar2", 4);
    	
    	System.out.println(timeBasedKeyValueStore.get("foo", 4));
    	System.out.println(timeBasedKeyValueStore.get("foo", 5));
    	
    	
    	timeBasedKeyValueStore.set("love","high", 10);
    	timeBasedKeyValueStore.set("love","low", 20);
    	
    	System.out.println(timeBasedKeyValueStore.get("love", 5));
    	System.out.println(timeBasedKeyValueStore.get("love", 10));
    	System.out.println(timeBasedKeyValueStore.get("love", 15));
    	System.out.println(timeBasedKeyValueStore.get("love", 20));
    	System.out.println(timeBasedKeyValueStore.get("love", 25));
    	
    	
	}

}
