import java.util.*;
import java.util.stream.Collectors;

public class Task_1 {
    public static void run() {
        int N = 20;
        Random random = new Random();

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(101);
        }
        System.out.println("1. Массив: " + Arrays.toString(array));

        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("2. Список: " + list);

        Collections.sort(list);
        System.out.println("3. По возрастанию: " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("4. В обратном порядке: " + list);

        Collections.shuffle(list);
        System.out.println("5. Перемешанный список: " + list);

        Collections.rotate(list, 1);
        System.out.println("6. Циклический сдвиг на 1: " + list);

        List<Integer> unique = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println("7. Только уникальные элементы: " + unique);

        List<Integer> duplicates = list.stream() //превращает в поток, чтобы обрабатывать по цепочке
                .filter(x -> Collections.frequency(list, x) > 1) //filter оставляет только те, которые удовл условию
                // frequency возвращает число вхождений x в list, distinct убирает повторы из потока
                .distinct()
                .collect(Collectors.toList()); // собирает поток в список
        System.out.println("8. Только дублирующиеся элементы: " + duplicates);

        Integer[] arrayFromList = list.toArray(new Integer[0]);
        System.out.println("9. Массив из списка: " + Arrays.toString(arrayFromList));

        Map<Integer, Integer> frequency = new HashMap<>(); // типа словарь
        for (Integer number : list) {
            frequency.merge(number, 1, Integer::sum); // к каждому элементу
            // присваивается 1 или +1 если уже был
        }
        System.out.println("10. Частота вхождений:");
        new TreeMap<>(frequency).forEach((k, v) -> System.out.println(k + " -> " + v));
    } // treemap сортирует ключи, foreach выводит ключ и значение
}