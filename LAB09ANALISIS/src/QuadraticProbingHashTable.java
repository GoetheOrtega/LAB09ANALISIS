public class QuadraticProbingHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size;
    private int capacity;
    private double loadFactor;
    private K[] keys;
    private V[] values;

    public QuadraticProbingHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public QuadraticProbingHashTable(int capacity, double loadFactor) {
        this.size = 0;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public void put(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize(2 * capacity);
        }
        int i = 0;
        int hash = hash(key, i);
        while (keys[hash] != null) {
            if (keys[hash].equals(key)) {
                values[hash] = value;
                return;
            }
            i++;
            hash = hash(key, i);
        }
        keys[hash] = key;
        values[hash] = value;
        size++;
    }

    public V get(K key) {
        int i = 0;
        int hash = hash(key, i);
        while (keys[hash] != null) {
            if (keys[hash].equals(key)) {
                return values[hash];
            }
            i++;
            hash = hash(key, i);
        }
        return null;
    }

    public void remove(K key) {
        int i = 0;
        int hash = hash(key, i);
        while (keys[hash] != null) {
            if (keys[hash].equals(key)) {
                keys[hash] = null;
                values[hash] = null;
                size--;
                return;
            }
            i++;
            hash = hash(key, i);
        }
    }

    public int size() {
        return size;
    }

    private int hash(K key, int i) {
        int hash1 = Math.abs(key.hashCode());
        int hash2 = Math.abs(7 - (key.hashCode() % 7));
        return (hash1 + i * hash2) % capacity;

    }

    private void resize(int newCapacity) {
        K[] newKeys = (K[]) new Object[newCapacity];
        V[] newValues = (V[]) new Object[newCapacity];
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                int j = 0;
                int hash = hash(keys[i], j);
                while (newKeys[hash] != null) {
                    j++;
                    hash = hash(keys[i], j);
                }
                newKeys[hash] = keys[i];
                newValues[hash] = values[i];
            }
        }
        keys = newKeys;
        values = newValues;
        capacity = newCapacity;
    }
}