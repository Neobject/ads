package lab3;

import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("1. Вивід стеку з цілими числами на екран в зворотньому порядку");
            System.out.println("2. Вивід двох стеків з парними та непарними числами серед введених з клавіатури");
            System.out.println("3. Перевірка правильності введення дужок ");
            System.out.println("4. Вихід");
            System.out.print("\nВиберіть дію: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    firstQuest();
                    break;
                case 2:
                    secondQuest();
                    break;
                case 3: {
                    System.out.print("Введіть послідовність дужок: ");
                    scanner.nextLine();  // Очищуємо буфер після попереднього nextInt
                    String sequence = scanner.nextLine();

                    // Використовуємо ExecutorService для обмеження часу вводу
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    Future<String> inputFuture = executorService.submit(() -> sequence);

                    final double maxExecutionTime = 10.0;

                    try {
                        // Очікуємо завершення вводу протягом вказаного максимального часу
                        String result = inputFuture.get((long) maxExecutionTime, TimeUnit.SECONDS);

                        // Перевіряємо баланс дужок
                        if (isBalanced(result)) {
                            System.out.println("Послідовність правильна.");
                        } else {
                            System.out.println("Послідовність Неправильна.");
                        }
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        System.out.println("\nВвід занадто довгий. Програма завершується. Натисніть ENTER для продовження");
                        scanner.nextLine();  // Очищуємо буфер після помилки
                    } finally {
                        executorService.shutdownNow();  // Завершуємо сервіс після виконання
                    }
                    break;
                }
                default:
                    break;
            }
        } while (choice != 4);
    }

    public static void firstQuest() {
        Scanner scanner = new Scanner(System.in);

        // Створюємо стек для зберігання цілих чисел
        Stack<Integer> myStack = new Stack<>();

        // Заповнюємо стек числами, введеними з клавіатури
        System.out.print("Введіть кількість елементів у стеку: ");
        int n = scanner.nextInt();

        System.out.println("Введіть цілі числа для стеку:");
        for (int i = 0; i < n; ++i) {
            int value = scanner.nextInt();
            myStack.push(value);
        }

        // Виводимо стек на екран у зворотньому порядку
        System.out.println("Елементи стеку у зворотньому порядку:");
        while (!myStack.isEmpty()) {
            System.out.print(myStack.pop() + " ");
        }
        System.out.println();
    }

    public static void secondQuest() {
        Scanner scanner = new Scanner(System.in);

        // Створюємо стеки для парних і непарних чисел
        Stack<Integer> evenStack = new Stack<>();
        Stack<Integer> oddStack = new Stack<>();

        // Заповнюємо стеки числами, введеними з клавіатури
        System.out.print("Введіть кількість елементів: ");
        int n1 = scanner.nextInt();

        System.out.println("Введіть цілі числа:");
        for (int i = 0; i < n1; ++i) {
            int value = scanner.nextInt();

            // Визначаємо, чи є число парним чи непарним
            if (value % 2 == 0) {
                evenStack.push(value);  // Додаємо парне число до стеку для парних чисел
            } else {
                oddStack.push(value);   // Додаємо непарне число до стеку для непарних чисел
            }
        }

        // Виводимо стек парних чисел
        System.out.println("Елементи стеку парних чисел:");
        while (!evenStack.isEmpty()) {
            System.out.print(evenStack.pop() + " ");
        }
        System.out.println();

        // Виводимо стек непарних чисел
        System.out.println("Елементи стеку непарних чисел:");
        while (!oddStack.isEmpty()) {
            System.out.print(oddStack.pop() + " ");
        }
        System.out.println();
    }

    public static boolean isBalanced(String sequence) {
        Stack<Character> brackets = new Stack<>();

        for (char bracket : sequence.toCharArray()) {
            if (bracket == '(' || bracket == '[') {
                brackets.push(bracket);  // Додаємо відкриту дужку до стеку
            } else if (bracket == ')' || bracket == ']') {
                // Перевіряємо відповідність закритої дужки відкритій
                if (brackets.isEmpty() ||
                        (bracket == ')' && brackets.peek() != '(') ||
                        (bracket == ']' && brackets.peek() != '[')) {
                    return false;
                }
                brackets.pop();  // Видаляємо відповідну відкриту дужку зі стеку
            }
        }

        // Перевіряємо, чи всі відкриті дужки були закриті
        return brackets.isEmpty();
    }
}