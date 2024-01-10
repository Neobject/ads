package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] n = input();
        int[] m = input();

        n = sort(n);
        System.out.println(Arrays.toString(n));

        System.out.println(Arrays.toString(search(m, n)));
    }

    private static int[] input() {
        System.out.println("Введіть числа:");
        Scanner scan = new Scanner(new Scanner(System.in).nextLine());
        ArrayList<Integer> list = new ArrayList<>();

        while (scan.hasNextInt()) {
            list.add(scan.nextInt());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array; // Масив вже відсортований або порожній, повертаємо без змін
        }

        int n = array.length;
        int[] tempArray = new int[n];
        sortAssist(array, tempArray, 0, n - 1);
        return array;
    }

    private static void sortAssist(int[] array, int[] tempArray, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Рекурсивно сортуємо ліву і праву частини масиву
            sortAssist(array, tempArray, left, middle);
            sortAssist(array, tempArray, middle + 1, right);

            // Зливаємо відсортовані частини
            merge(array, tempArray, left, middle, right);
        }
    }

    private static void merge(int[] array, int[] tempArray, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (tempArray[i] <= tempArray[j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = tempArray[i];
            i++;
            k++;
        }

        while (j <= right) {
            array[k] = tempArray[j];
            j++;
            k++;
        }
    }

    private static int[] search(int[] source, int[] sample) {
        int[] result = new int[source.length];

        for (int i = 0; i < source.length; i++) {
            int position = binarySearch(sample, source[i]);
            result[i] = position;
        }

        return result;
    }

    private static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid; // Знайдено елемент у вибірці
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Елемент не знайдено у вибірці
    }
}