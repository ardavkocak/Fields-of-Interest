import java.util.HashSet;
import java.util.Set;

public class HashMapim<K, V> {

    private static final int baslangicKapasite = 10;
    private static final double arttirma = 0.75;

    private int size;
    private int capacity;
    private ArrayListim<Entry<K, V>>[] table;

    @SuppressWarnings("unchecked")
    private final ArrayListim<Entry<K, V>>[] tablem = (ArrayListim<Entry<K, V>>[]) new ArrayListim<?>[capacity];


    public HashMapim() {
        this(baslangicKapasite);
    }

    public HashMapim(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new ArrayListim[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ArrayListim<>();
        }
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (size >= capacity * arttirma) {
            resize();
        }

        int index = hash(key);
        ArrayListim<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        ArrayListim<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null; // Key not found
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();

        for (int i = 0; i < table.length; i++) {
            ArrayListim<Entry<K, V>> bucket = table[i];
            for (int j = 0; j < bucket.size(); j++) {
                Entry<K, V> entry = bucket.get(j);
                keySet.add(entry.getKey());
            }
        }

        return keySet;
    }


    public void remove(K key) {
        int index = hash(key);
        ArrayListim<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }



    public Set<HashMapim.Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (ArrayListim<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                entrySet.add(entry);
            }
        }
        return entrySet;
    }


    public boolean containsKey(K key) {
        int index = hash(key);
        ArrayListim<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        for(int i =0;i<table.length;i++) {
            ArrayListim<Entry<K, V>> bucket = table[i];
            for (i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }

        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i].clear();
        }
        size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0; // veya başka bir varsayılan değer
        }
        return Math.abs(key.hashCode()) % capacity;
    }


    private void resize() {
        int newCapacity = capacity * 2;
        ArrayListim<Entry<K, V>>[] newTable = new ArrayListim[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new ArrayListim<>();
        }

        for (ArrayListim<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                int index = Math.abs(entry.getKey().hashCode()) % newCapacity;
                newTable[index].add(entry);
            }

        }

        table = newTable;
        capacity = newCapacity;
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
