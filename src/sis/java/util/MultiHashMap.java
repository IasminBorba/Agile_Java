package util;

import java.util.HashMap;
import java.util.*;

public class MultiHashMap<K, V> {
    private final Map<K, List<V>> map = new HashMap<>();

    public int size(){
        return map.size();
    }

    public void put(K key, V value){
        List<V> values = map.computeIfAbsent(key, k -> new ArrayList<>());
        values.add(value);
    }

    public List<V> get(K key){
        return map.get(key);
    }
}
