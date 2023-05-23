
public class LinearProbingHashTable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry[] table;
    private int size;
    private int capacity;
    private double loadFactor;

    public LinearProbingHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public LinearProbingHashTable(int capacity, double loadFactor) {
        this.table = new Entry[capacity];
        this.size = 0;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    public void put(String key, int value) {
        if (size >= capacity * loadFactor) {
            resize();
        }

        int index = hash(key);

        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
        }

        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
        } else {
            table[index].value = value;
        }
    }

    public int get(String key) {
        int index = hash(key);

        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
        }

        if (table[index] != null) {
            return table[index].value;
        }

        return -1; // Key not found
    }

    public void remove(String key) {
        int index = hash(key);

        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
        }

        if (table[index] != null) {
            table[index] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int hash(String key) {
        char firstChar = key.charAt(0);
        return (11 * firstChar) % capacity;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry[] newTable = new Entry[newCapacity];

        for (Entry entry : table) {
            if (entry != null) {
                int index = hash(entry.key);

                while (newTable[index] != null) {
                    index = (index + 1) % newCapacity;
                }

                newTable[index] = entry;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    private class Entry {
        private String key;
        private int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();

        // Insertar valores en la tabla hash
        hashTable.put("E", 5);
        hashTable.put("A", 1);
        hashTable.put("S", 19);
        hashTable.put("Y", 25);
        hashTable.put("Q", 17);
        hashTable.put("U", 21);
        hashTable.put("T", 20);
        hashTable.put("I", 9);
        hashTable.put("O", 15);
        hashTable.put("N", 14);

        // Obtener valores de la tabla hash
        System.out.println("Valor de la clave 'S': " + hashTable.get("S"));
        System.out.println("Valor de la clave 'O': " + hashTable.get("O"));
        System.out.println("Valor de la clave 'E': " + hashTable.get("E"));
        System.out.println("Valor de la clave 'X': " + hashTable.get("X"));

        // Eliminar un valor de la tabla hash
        hashTable.remove("T");

        // Obtener el tamaño de la tabla hash
        System.out.println("Tamaño de la tabla hash: " + hashTable.size());
    }
}
