package lab4;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> priority = new PriorityQueue<>();

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------\n" +
                    "1.  Додати числа на початок деки\n" +
                    "2.  Додати числа в кінець деки\n" +
                    "3.  Видалити перший елемент деки\n" +
                    "4.  Видалити останній елемент деки\n" +
                    "5.  Очистити деку\n" +
                    "6.  Вивести деку\n" +
                    "-----------------------\n" +
                    "7.  Перевірити рядок на паліндром\n" +
                    "-----------------------\n" +
                    "8.  Додати числа в пріоритетну чергу\n" +
                    "9.  Видалити останній елемент пріоритетної черги\n" +
                    "10. Очистити пріоритетну чергу\n" +
                    "11. Вивести пріоритетну чергу");

            int value = scan.nextInt();

            switch (value) {
                case 1:
                    add(deque::addFirst);
                    break;
                case 2:
                    add(deque::addLast);
                    break;
                case 3:
                    remove(deque::removeFirst);
                    break;
                case 4:
                    remove(deque::removeLast);
                    break;
                case 5:
                    clear(deque);
                    break;
                case 6:
                    System.out.println(deque);
                    break;
                case 7:
                    checkPalindrome();
                    break;
                case 8:
                    add(priority::add);
                    break;
                case 9:
                    remove(priority::remove);
                    break;
                case 10:
                    clear(priority);
                    break;
                case 11:
                    System.out.println(priority);
                    break;

                default: System.exit(0);
            }
        }
    }

    private static void add(Consumer<Integer> operation) {
        System.out.println("Введіть числа:");
        Scanner scan = new Scanner(new Scanner(System.in).nextLine());

        while (scan.hasNextInt()) {
            operation.accept(scan.nextInt());
        }
    }

    private static void remove(Supplier<Integer> operation) {
        System.out.println("Видалено " + operation.get());
    }

    private static void clear(Queue<?> queue) {
        System.out.println("Видалено елементів " + queue.size());
        queue.clear();
    }

    private static void checkPalindrome() {
        System.out.println("Введіть слово:");
        Deque<Character> word = new Scanner(System.in).nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayDeque::new));

        while (word.size() > 1) {
            System.out.println(word.size());
            if (!word.removeFirst().equals(word.removeLast())) {
                System.out.println("Не паліндром");
                return;
            }
        }

        System.out.println("Паліндром");
    }
}