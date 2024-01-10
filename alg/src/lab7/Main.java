package lab7;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введіть довжину масиву:");
        int size = new Scanner(System.in).nextInt();
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(-100, 100);
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.print("\n");
        }

        int last = Integer.MIN_VALUE, x = -1, y = -1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[j][i] > 0) {
                    last = matrix[j][i];
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(last + " (" + x + "; " + y + ")");
    }
}