package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static BinaryTree<Integer, Integer> numbers;
    private static BinaryTree<Integer, Character> letters;
    private static BinaryTree<String, Character> words;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------\n" +
                    "1. Побудувати дерево з чисел\n" +
                    "2. Знайти найбільше число\n" +
                    "-----------------------\n" +
                    "3. Побудувати дерево з букв рядка\n" +
                    "4. Видалити букви, що повторюються\n" +
                    "-----------------------\n" +
                    "5. Побудувати дерево зі слів текстового файлу\n" +
                    "6. Видалити перший елемент деки");

            int value = scan.nextInt();

            switch (value) {
                case 1:
                    (numbers = formNumbersTree()).walkThrow();
                    break;
                case 2:
                    System.out.println(getMaxNumber(numbers.getRoot(), Integer.MIN_VALUE));
                    break;
                case 3:
                    (letters = formLettersTree()).walkThrow();
                    break;
                case 4:
                    System.out.println(removeDuplicates(letters));
                    letters.walkThrow();
                    break;
                case 5:
                    (words = formWordsTree()).walkThrow();
                    break;
                case 6:
                    System.out.println("-" + removeWords(words));
                    words.walkThrow();
                    break;

                default: System.exit(0);
            }
        }
    }

    private static BinaryTree<Integer, Integer> formNumbersTree() {
        System.out.println("Введіть числа:");
        Scanner scan = new Scanner(new Scanner(System.in).nextLine());
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();

        while (scan.hasNextInt()) {
            int number = scan.nextInt();
            tree.insert(number, number);
        }

        return tree;
    }

    private static int getMaxNumber(TreeNode<Integer, Integer> node, int max) {
        if (node == null) {
            return max;
        }

        if (node.getValue() > max) {
            max = node.getValue();
        }

        max = getMaxNumber(node.getRight(), max);
        max = getMaxNumber(node.getLeft(), max);

        return max;
    }

    private static BinaryTree<Integer, Character> formLettersTree() {
        System.out.println("Введіть рядок:");
        String letters = new Scanner(System.in).nextLine();
        BinaryTree<Integer, Character> tree = new BinaryTree<>();

        for (int i = 0; i < letters.length(); i++) {
        if (Character.isLetter(letters.charAt(i))) {
            tree.insert(i, letters.charAt(i));
        }
        }

        return tree;
    }

    private static Set<Character> removeDuplicates(BinaryTree<Integer, Character> tree) {
        List<Character> chars = new LinkedList<>();
        Set<Character> duplicates = new HashSet<>();

        letters.foreach(node -> true, node -> {
            if (chars.contains(node.getValue())) {
                duplicates.add(node.getValue());
            }
            else {
                chars.add(node.getValue());
            }
        });

        letters.remove(node -> duplicates.contains(node.getValue()));
        return duplicates;
    }

    private static BinaryTree<String, Character> formWordsTree() {
        BinaryTree<String, Character> tree = new BinaryTree<>();
        System.out.println("Введіть шлях до файлу:");

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(new Scanner(System.in).nextLine())))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Scanner words = new Scanner(line);

                while (words.hasNext()) {
                    String word = words.next();
                    tree.insert(word, word.charAt(0));
                }
            }
        } catch (IOException e) {
            System.out.println("Нема такого файлу");
        }

        return tree;
    }

    private static int removeWords(BinaryTree<String, Character> tree) {
        System.out.println("Введіть літеру:");
        char letter = new Scanner(System.in).nextLine().charAt(0);
        int count = tree.where(node -> node.getValue() == letter);
        tree.remove(node -> node.getValue() == letter);
        return count;
    }
}