package com.krushna.Java_rnd.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LFUCache<K, V> {

	int capacity;
	Map<K, V> cacheEntries;
	Map<K, Integer> keyFrequency;

	int minFrequency = 1;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		cacheEntries = HashMap.newHashMap(capacity);
		keyFrequency = HashMap.newHashMap(capacity);
	}

	Map<Integer, List<K>> frequncyKeysMap = new HashMap<>();

	public void put(K k, V v) {
		if (capacity <= 0) {
			throw new RuntimeException("capacity is zero");
		}
		// check the size and then remove key
		if (cacheEntries.size() > capacity) {
			// remove less used key
			evictKey();
		}

		if (cacheEntries.containsKey(k)) {
			increaseFrequency(k);
		} else {
			minFrequency = 1;
			keyFrequency.put(k, 1);
			frequncyKeysMap.computeIfAbsent(1, ignore -> new LinkedList<K>()).add(k);
		}
		cacheEntries.put(k, v);

	}

	public V get(K k) {
		V v = cacheEntries.get(k);
		if (v != null) {
			increaseFrequency(k);
		}
		return v;
	}

	private void evictKey() {
		List<K> keys = frequncyKeysMap.get(minFrequency);
		K key = keys.remove(0);
		cacheEntries.remove(key);
		keyFrequency.remove(key);

		// remove the frequency entry which key list is empty now
		if (keys.isEmpty()) {
			keyFrequency.remove(minFrequency);
		}
	}

	private void increaseFrequency(K key) {
		int frequncy = keyFrequency.get(key);
		keyFrequency.put(key, frequncy + 1);
		// remove from old keyList and move to new one
		List<K> keys = frequncyKeysMap.get(frequncy);
		keys.remove(key);

		if (keys.isEmpty()) {
			frequncyKeysMap.remove(frequncy);
			if (frequncy == minFrequency)
				minFrequency++;
		}
		frequncyKeysMap.computeIfAbsent(frequncy + 1, keyValue -> new LinkedList<K>()).add(key);
	}

	public static void main(String[] args) {
		LFUCache<String, String> cache = new LFUCache<String, String>(3);

		cache.put("key1", "v0");
		cache.put("key2", "v2");
		cache.put("key1", "v1");
		cache.put("key3", "v3");
		cache.put("key4", "v4");
		//
		System.out.println(cache.cacheEntries);
		System.out.println(cache.frequncyKeysMap);

		System.out.println(cache.get("key3"));
		System.out.println(cache.get("key3"));
		System.out.println(cache.get("key3"));
		System.out.println(cache.get("key3"));

		cache.put("key4", "v4");
		cache.put("key5", "v5");
		cache.put("key6", "v6");

		System.out.println(cache.cacheEntries);
		System.out.println(cache.frequncyKeysMap);

	}

}
