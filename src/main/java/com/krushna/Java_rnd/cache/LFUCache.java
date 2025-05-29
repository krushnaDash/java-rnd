package com.krushna.Java_rnd.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> {

    private final int capacity;
    private int minFreq = 0;

    private final Map<K, V> values = new HashMap<>();
    private final Map<K, Integer> freqs = new HashMap<>();
    private final Map<Integer, LinkedHashSet<K>> freqLists = new HashMap<>();

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
        freqs.put(key, 1);
        freqLists.computeIfAbsent(1, ignore -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    private void increaseFreq(K key) {
        int freq = freqs.get(key);
        freqs.put(key, freq + 1);
        freqLists.get(freq).remove(key);

        if (freqLists.get(freq).isEmpty()) {
            freqLists.remove(freq);
            if (freq == minFreq) minFreq++;
        }

        freqLists.computeIfAbsent(freq + 1, ignore -> new LinkedHashSet<>()).add(key);
    }

    private void evictLFU() {
        LinkedHashSet<K> keys = freqLists.get(minFreq);
        K evictKey = keys.iterator().next();
        keys.remove(evictKey);

        if (keys.isEmpty()) freqLists.remove(minFreq);

        values.remove(evictKey);
        freqs.remove(evictKey);
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
