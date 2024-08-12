package util;

import java.util.*;

public class MultiHashMap<K, V> {
    private final Map<K, List<V>> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void put(K key, V value) {
        List<V> values = map.computeIfAbsent(key, k -> new ArrayList<>());
        values.add(value);
    }

    public List<V> get(K key) {
        return map.get(key);
    }

    protected Set<Map.Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }

    public interface Filter<T> {
        boolean apply(T item);
    }

    public static <K, V> void filter(final MultiHashMap<K, ? super V> target, final MultiHashMap<K, V> source, final Filter<? super V> filter) {
        for (K key : source.keys()) {
            final List<V> values = source.get(key);
            for (V value : values)
                if (filter.apply(value))
                    target.put(key, value);
        }
    }

    public Set<K> keys() {
        return map.keySet();
    }
}
