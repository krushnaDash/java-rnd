package com.krushna.Java_rnd.cache;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // true = access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    // Demo
    public static void main(String[] args) {
    	
    	
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");  // [1]
        cache.put(2, "B");  // [1, 2]
        cache.put(3, "C");  // [1, 2, 3]
        System.out.println(cache);   // {1=A, 2=B, 3=C}

        cache.get(1);       // Access 1 → [2, 3, 1]
        System.out.println(cache); 
        cache.put(4, "D");  // Evict 2 → [3, 1, 4]
        System.out.println(cache);   // {3=C, 1=A, 4=D}
    }
}
