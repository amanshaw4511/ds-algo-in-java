package com.com.aman.ds;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.ArrayList;
import java.util.Iterator;

class Entry<K, V> {
    K key;
    V value;
    int hash;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if (this.hash != other.hash) {
            return false;
        }
        return this.key.equals(other.key);
    }

    @Override
    public String toString() {
        return this.key + " => " + this.value;
    }
}

@SuppressWarnings("unchecked")
public class HMap<K, V> implements Iterable<Entry<K,V>> {
    private int size;
    private int capacity;
    private double maxLoadFactor;
    private int threshold;
    private LinkedList<Entry<K, V>>[] data;

    HMap() {
        this(16, 0.75);
    }

    HMap(int capacity) {
        this(capacity, 0.75);
    }

    HMap(int capacity, double maxLoadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + capacity);
        }
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)
                || maxLoadFactor >= 1) {
            throw new IllegalArgumentException("Illegal maxLoadFactor " + maxLoadFactor);
        }

        this.size = 0;
        this.capacity = capacity;
        this.maxLoadFactor = maxLoadFactor;
        this.threshold = (int) (this.capacity * this.maxLoadFactor);
        this.data = new LinkedList[16];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int capacity() {
        return this.capacity;
    }

    private void ensureExtraCapacity() {
        if (this.size <= this.threshold) {
            return;
        }

        this.capacity *= 2;
        LinkedList<Entry<K, V>>[] newData = new LinkedList[this.capacity];
        for (LinkedList<Entry<K, V>> bucket : this.data) {
            if (bucket == null) {
                continue;
            }

            for (Entry<K, V> entry : bucket) {
                int index = this.normalizeIndex(entry.hash);
                LinkedList<Entry<K, V>> newBucket = newData[index];
                if (newBucket == null) {
                    newBucket = new LinkedList<Entry<K, V>>();
                    newData[index] = newBucket;
                }
                newBucket.add(entry);
            }
        }
        this.data = newData;
    }

    @Override
    public Iterator<Entry<K,V>> iterator() {
        return new Iterator<Entry<K,V>>() {
            int bucketIndex = 0;
            Iterator<Entry<K, V>> bucketIter = (data[0] == null) ? null : data[0].iterator();

            @Override
            public boolean hasNext() {
                if (bucketIter == null || !bucketIter.hasNext()) {
                    while (++bucketIndex < capacity) {
                        if (data[bucketIndex] != null) {
                            if (data[bucketIndex].iterator().hasNext()) {
                                bucketIter = data[bucketIndex].iterator();
                                break;
                            }
                        }
                    }
                }
                return bucketIndex < capacity;
            }

            @Override
            public Entry<K,V> next() {
                return bucketIter.next();
            }
        };
    }

    public boolean containsKey(K key) {
        int index = this.normalizeIndex(key.hashCode());
        return this.getEntry(index, key) != null;
    }

    public int normalizeIndex(int keyHash) {
        // remove msb (- sign bit) and mod with capacity
        return (keyHash & 0x7FFFFFFF) % this.capacity;
    }

    public V set(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        int index = this.normalizeIndex(key.hashCode());
        Entry<K, V> existingEntry = this.getEntry(index, key);

        if (existingEntry == null) {
            LinkedList<Entry<K, V>> bucket = this.data[index];
            if (bucket == null) {
                bucket = new LinkedList<Entry<K, V>>();
                this.data[index] = bucket;
            }

            bucket.add(new Entry<>(key, value));
            this.size += 1;
            this.ensureExtraCapacity();
            return null;
        }

        V oldValue = existingEntry.value;
        existingEntry.value = newEntry.value;
        return oldValue;
    }

    public V get(K key) {
        int index = this.normalizeIndex(key.hashCode());
        return this.getEntry(index, key).value;
    }

    public V remove(K key) {
        int index = this.normalizeIndex(key.hashCode());
        Entry<K, V> entry = this.getEntry(index, key);

        if (entry == null) {
            return null;
        }
        this.data[index].remove(entry);
        this.size -= 1;
        return entry.value;
    }

    private Entry<K, V> getEntry(int index, K key) {
        if (key == null) {
            return null;
        }
        LinkedList<Entry<K, V>> bucket = this.data[index];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    public List<K> keys() {
        return StreamSupport.stream(this.spliterator(), false)
            .map(e -> e.key)
            .collect(Collectors.toList());
    }

    public List<V> values() {
        return StreamSupport.stream(this.spliterator(), false)
            .map(e -> e.value)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "{ }";
        }
        StringBuilder sb = new StringBuilder("{");
        this.forEach(e -> sb.append(e + ", "));
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

}
