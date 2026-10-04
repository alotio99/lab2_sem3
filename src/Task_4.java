import java.util.*;

public class Task_4 {
    public static void run() {
        String text = "Hello world! Hello Java. Java is powerful, and the world is big. HELLO again, hello world!";

        Map<String, Integer> frequency = new HashMap<>(); // самый быстрый и простой вариант для подсчёта
        String[] words = text.toLowerCase().split("[^a-z]+"); // split разрезаем где не буква

        for (String word : words) {
            if (word.isEmpty()) continue;
            frequency.merge(word, 1, Integer::sum);
        }

        System.out.println("Частота слов:");
        frequency.forEach((word, count) -> System.out.println(word + " -> " + count));

        System.out.println("\nВ алфавитном порядке:");
        new TreeMap<>(frequency).forEach((word, count) -> System.out.println(word + " -> " + count));
    }
}

