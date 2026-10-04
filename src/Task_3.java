import java.util.*;

public class Task_3 {
    public static void run() {
        List<Human> people = Arrays.asList(
                new Human("Ivan", "Petrov", 25),
                new Human("Anna", "Ivanova", 30),
                new Human("Petr", "Sidorov", 25),
                new Human("Elena", "Petrova", 28),
                new Human("Oleg", "Ivanov", 35),
                new Human("Ivan", "Petrov", 25)
        );

        System.out.println("Исходный список:");
        people.forEach(System.out::println);

        Set<Human> hashSet = new HashSet<>(people);
        System.out.println("\nHashSet:");
        hashSet.forEach(System.out::println);
//        сравнивает через hashCode и equals
//        дубликат удалён - 5 человек
//        порядок случайный

        Set<Human> linkedHashSet = new LinkedHashSet<>(people);
        System.out.println("\nLinkedHashSet:");
        linkedHashSet.forEach(System.out::println);
//        тоже hashCode и equals
//        дубликат удалён - человек
//        порядок - порядок добавления

        Set<Human> treeSet = new TreeSet<>(people);
        System.out.println("\nTreeSet с естественным порядком Comparable:");
        treeSet.forEach(System.out::println);
//        сравнивает через compareTo
//        дубликат удалён - 5 человек
//        порядок - отсортированный

        Set<Human> treeByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeByLastName.addAll(people);
        System.out.println("\nTreeSet с HumanComparatorByLastName:");
        treeByLastName.forEach(System.out::println);
//        сравнивает только фамилию
//        люди с одинаковой фамилией - дубликаты
//        в списке фамилии разные - 5 человек
//        порядок - по фамилии

        Set<Human> treeByAge = new TreeSet<>(new Comparator<Human>() {
            @Override // анонимный - прямо на месте, без класса
            public int compare(Human first, Human second) {
                return Integer.compare(first.getAge(), second.getAge());
            }
        });
        treeByAge.addAll(people);
        System.out.println("\nTreeSet с анонимным компаратором по возрасту:");
        treeByAge.forEach(System.out::println);
//        сравнивает только возраст
//        люди одного возраста - дубликаты
//        возраст 25 у трёх - остался только первый всего 4
//        порядок - по возрасту
    }
}

class Human implements Comparable<Human> {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    @Override
    public int compareTo(Human other) { // Comparable
        int result = lastName.compareTo(other.lastName); // .compareTo лексикографически сравнивает строки
        if (result != 0) return result;
        result = firstName.compareTo(other.firstName);
        if (result != 0) return result;
        return Integer.compare(age, other.age); // возвращает -1 0 1
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // является ли это тем же самым объектом
        if (!(o instanceof Human)) return false; // является ли Human
        Human human = (Human) o; // приведение, т.к. может быть объявлен как Object
        return age == human.age
                && firstName.equals(human.firstName)
                && lastName.equals(human.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", возраст: " + age;
    }
}

class HumanComparatorByLastName implements Comparator<Human> {
    @Override
    public int compare(Human first, Human second) {
        return first.getLastName().compareTo(second.getLastName());
    }
}