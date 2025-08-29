package com.krushna.Java_rnd.neetcode.binarysearch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This one is a more efficient implementation of TimeBasedKeyValueStore.
 * Using TreeMap to store the values against the timestamp.
 * @param <K>
 * @param <V>
 */
public class TimeMap<K,V> {
	
	Map<K, TreeMap<Integer, V>> timeMap;
	
	TimeMap(){
		timeMap= new HashMap<>();
	}
	
	public void set(K key, V value, int timestamp) {
    	timeMap.computeIfAbsent(key,  (ignoreKey) -> new TreeMap<>()).put(timestamp, value);
    }
	
	public V get(String key, int timestamp) {
		return timeMap.get(key).floorEntry(timestamp).getValue();
	}
	public static void main(String[] args) {
		TimeMap<String, String> timeMap = new TimeMap<>();
		timeMap.set("foo", "bar", 1);
		System.out.println(timeMap.get("foo", 1)); // returns "bar"
		System.out.println(timeMap.get("foo", 3)); // returns "bar"
		timeMap.set("foo", "bar2", 4);
		System.out.println(timeMap.get("foo", 4)); // returns "bar2"
		System.out.println(timeMap.get("foo", 5)); // returns "bar2"
		
		
		timeMap.set("love","high", 10);
		timeMap.set("love","low", 20);
    	
    	System.out.println(timeMap.get("love", 5));
    	System.out.println(timeMap.get("love", 10));
    	System.out.println(timeMap.get("love", 15));
    	System.out.println(timeMap.get("love", 20));
    	System.out.println(timeMap.get("love", 25));
	}

}
