import java.util.*;

public class Task2_PrimesGeneratorTest {
    public static void run() {
        int N = 10;
        PrimesGenerator generator = new PrimesGenerator(N);

        System.out.println("Простые числа в прямом порядке:");
        Iterator<Integer> iterator = generator.iterator();
        while (iterator.hasNext()) { // есть ли следующий
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        List<Integer> primes = new ArrayList<>();
        iterator = generator.iterator();
        while (iterator.hasNext()) {
            primes.add(iterator.next());
        }

        System.out.println("Простые числа в обратном порядке:");
        ListIterator<Integer> reverseIterator = primes.listIterator(primes.size()); // итератор после последнего элемента
        while (reverseIterator.hasPrevious()) {
            System.out.print(reverseIterator.previous() + " ");
        }
        System.out.println();
    }
}

class PrimesGenerator implements Iterable<Integer> {
    private final int count; // задается один раз и не может быть изменено

    public PrimesGenerator(int count) {
        this.count = count;
    } // конструктор

    @Override // переопределение
    public Iterator<Integer> iterator() {
        return new PrimeIterator(count);
    }

    private static class PrimeIterator implements Iterator<Integer> { // реализует методы Iterator
        private int remaining; // количество чисел для проверки
        private int candidate = 2; // число, которое проверяется на простоту

        public PrimeIterator(int count) {
            this.remaining = count;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();

            while (!isPrime(candidate)) {
                candidate++;
            }
            int result = candidate++; // ++ возвращает старое значение, а потом увеличивает
            remaining--;
            return result;
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;

            for (int i = 3; i * i <= n; i += 2) { // i * i <= n эквивалентно i <= √n
                if (n % i == 0) return false;
            }
            return true;
        }
    }
}
