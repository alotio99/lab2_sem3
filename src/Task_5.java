import java.util.*;

// Подвох в том, что значения могут повторяться, а ключи в Map обязаны быть уникальными

public class Task_5 {

    public static <K, V> Map<V, K> invertSimple(Map<K, V> source) {
        Map<V, K> result = new LinkedHashMap<>(); // меняем V, K; LinkedHashMap — чтобы сохранить порядок добавления
        for (Map.Entry<K, V> entry : source.entrySet()) { // source.entrySet() возвращает множество пар Map
            result.put(entry.getValue(), entry.getKey());
        }
        return result; // одинаковые значения убирают ключи
    }

    public static <K, V> Map<V, List<K>> invertSafe(Map<K, V> source) {
        Map<V, List<K>> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : source.entrySet()) {
            result.computeIfAbsent(entry.getValue(), k -> new ArrayList<>())
                    .add(entry.getKey()); // computeIfAbsent находит список или создаёт новый
        }
        return result;
    }

    public static void run() {
        Map<String, Integer> original = new LinkedHashMap<>();
        original.put("one", 1);
        original.put("two", 2);
        original.put("three", 3);
        original.put("uno", 1);

        System.out.println("Исходная Map: " + original);
        System.out.println("\nПростой invertSimple:");
        System.out.println(invertSimple(original));
        System.out.println("\nБезопасный invertSafe:");
        System.out.println(invertSafe(original));
    }
}
