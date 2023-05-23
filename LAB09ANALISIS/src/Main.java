import java.util.Hashtable;
public class Main {
    public static void main(String[] args) {
        QuadraticProbingHashTable<String, Integer> quadraticProbingHashTable = new QuadraticProbingHashTable<>();
        quadraticProbingHashTable.put("A", 1);
        quadraticProbingHashTable.put("B", 2);
        quadraticProbingHashTable.put("C", 3);

        System.out.println(quadraticProbingHashTable.get("A")); // 1
        System.out.println(quadraticProbingHashTable.get("B")); // 2
        System.out.println(quadraticProbingHashTable.get("C")); // 3
        System.out.println(quadraticProbingHashTable.get("D")); // null

        quadraticProbingHashTable.remove("B");

        System.out.println(quadraticProbingHashTable.get("B")); // null

        System.out.println(quadraticProbingHashTable.size()); // 2
    }
}
