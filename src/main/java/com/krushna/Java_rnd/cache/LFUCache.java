package com.krushna.Java_rnd.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Salesforce LLd
 * @param <K>
 * @param <V>
 */
public class LFUCache<K, V> {

    private final int capacity;
    private int minFreq = 0;

    private final Map<K, V> values = new HashMap<>();
    private final Map<K, Integer> keyFreqsMap = new HashMap<>();
    private final Map<Integer, LinkedHashSet<K>> freqMapList = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!values.containsKey(key)) return null;
        increaseFreq(key);
        return values.get(key);
    }

    public void put(K key, V value) {
        if (capacity == 0) return;

        if (values.containsKey(key)) {
            values.put(key, value);
            increaseFreq(key);
            return;
        }

        if (values.size() >= capacity) {
            evictLFU();
        }

        values.put(key, value);
        keyFreqsMap.put(key, 1);
        freqMapList.computeIfAbsent(1, ignore -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    private void increaseFreq(K key) {
        int freq = keyFreqsMap.get(key);
        keyFreqsMap.put(key, freq + 1);
        freqMapList.get(freq).remove(key);

        if (freqMapList.get(freq).isEmpty()) {
            freqMapList.remove(freq);
            if (freq == minFreq) minFreq++;
        }

        freqMapList.computeIfAbsent(freq + 1, ignore -> new LinkedHashSet<>()).add(key);
    }

    private void evictLFU() {
        LinkedHashSet<K> keys = freqMapList.get(minFreq);
        K evictKey = keys.iterator().next();
        keys.remove(evictKey);

        if (keys.isEmpty()) freqMapList.remove(minFreq);

        values.remove(evictKey);
        keyFreqsMap.remove(evictKey);
    }

    // Demo
    public static void main(String[] args) {
        LFUCache<Integer, String> cache = new LFUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.get(1);  // freq: 1 → 2
        cache.get(1);  // freq: 2 → 3
        cache.put(4, "D"); // evict key with least freq (2 or 3)
        System.out.println(cache.values); // Expect key 1, 4, and one of 2/3
    }
}
