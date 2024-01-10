package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // Введення розміру масиву та діапазону для генерації випадкових чисел
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[scan(scanner, "Введіть розмір масиву (N)")];
        int min = scan(scanner, "Введіть нижню границю для генерації чисел");
        int max = scan(scanner, "Введіть верхню границю для генерації чисел");
        scanner.close();

        // Створення та заповнення масиву випадковими числами
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = (int) (Math.random() * (max - min + 1)) + min;

        // Виведення початкового масиву на екран
        System.out.print("Початковий масив: ");
        print(numbers);

        //Знаходження суми та кількості додатних непарних елементів
        int sum = 0,  count = 0;
        for (int number : numbers)
        {
            if ((number % 2) == 1)
            {
                sum += number;
                count++;
            }
        }

        //ЇХ вивід
        System.out.println("Кількість додатніх парних елементів: " + count);
        System.out.println("Їх сума: " + sum);

        // Видалення елементів з парними індексами
        int[] oddNumbers = new int[(numbers.length + 1) / 2];
        for (int i = 1, j = 0; i < numbers.length; i += 2, j++)
            oddNumbers[j] = numbers[i];

        // Виведення масиву після видалення елементів на екран
        System.out.print("Масив після видалення елементів з парними індексами: ");
        print(oddNumbers);
    }

    //Метод, що зчитує число
    private static int scan(Scanner scanner, String message)
    {
        System.out.print(message + ": ");
        return scanner.nextInt();
    }

    // Метод для виведення масиву на екран
    private static void print(int[] array)
    {
        for (int value : array)
            System.out.print(value + " ");

        System.out.println();
    }
}