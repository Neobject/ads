package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Family> families = new ArrayList<>();

    static class Date {
        int day;
        int month;
        int year;

        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public String toString() {
            return day + "/" + month + "/" + year;
        }
    }

    static class Job {
        boolean employed;
        String profession;
        int experience;

        public Job(boolean employed, String profession, int experience) {
            this.employed = employed;
            this.profession = profession;
            this.experience = experience;
        }

        @Override
        public String toString() {
            if (employed) {
                return "Зайнятий у сфері " + profession + " з досвідом " + experience + " років";
            } else {
                return "Не зайнятий на роботі";
            }
        }
    }

    static class Person {
        String firstName;
        String lastName;
        Date birthDate;
        Job job;

        public Person(String firstName, String lastName, Date birthDate, Job job) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.job = job;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", народжений " + birthDate + ", " + job;
        }

        public boolean isFemale() {
            return firstName.toLowerCase().endsWith("а");
        }
    }

    static class Family {
        Person husband;
        Person wife;
        List<Person> children;

        public Family(Person husband, Person wife, List<Person> children) {
            this.husband = husband;
            this.wife = wife;
            this.children = children;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Чоловік: ").append(husband).append("\n");
            result.append("Дружина: ").append(wife).append("\n");
            result.append("Діти:\n");
            for (Person child : children) {
                result.append("  ").append(child).append("\n");
            }
            return result.toString();
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public Person getWife() {
            return wife;
        }
    }

    public static void main(String[] args) {
        families.add(new Family(
                new Person("Іван", "Петров", new Date(10, 5, 1980), new Job(true, "Інженер", 15)),
                new Person("Марія", "Петрова", new Date(15, 8, 1982), new Job(true, "Вчитель", 10)),
                Arrays.asList(
                        new Person("Олег", "Петров", new Date(3, 2, 2005), new Job(false, "", 0)),
                        new Person("Анна", "Петрова", new Date(7, 12, 2008), new Job(false, "", 0))
                )
        ));

        families.add(new Family(
                new Person("Петро", "Іванов", new Date(20, 3, 1975), new Job(true, "Лікар", 20)),
                new Person("Ольга", "Іванова", new Date(25, 6, 1980), new Job(true, "Медсестра", 18)),
                Arrays.asList(
                        new Person("Ірина", "Іванова", new Date(10, 9, 2002), new Job(false, "", 0)),
                        new Person("Сергій", "Іванов", new Date(5, 11, 2005), new Job(false, "", 0))
                )
        ));
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Оберіть опцію:");
            System.out.println("0. Внесення цілих чисел в список");
            System.out.println("1. Перетворення рядка в список символів");
            System.out.println("2. Перетворення рядка в список атомів");
            System.out.println("3. Ввести дані для нової сім'ї");
            System.out.println("4. Вивести всі родини з двома дітьми");
            System.out.println("5. Вивести прізвища всіх жінок з двома дітьми");
            System.out.println("6. Вивести імена і прізвища всіх людей в базі даних");
            System.out.println("7. Вивести імена й прізвища всіх жінок які працюють");
            System.out.println("8. Вивести імена й прізвища людей чий стаж роботи менше 10 років");
            System.out.println("9. Вихід");

            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    readIntegersToList(scanner);
                    break;
                case 1:
                    convertStringToCharList(scanner);
                    break;
                case 2:
                    System.out.println("Введіть рядок:");
                    scanner.nextLine();  // Виводимо новий рядок, щоб очистити буфер введення
                    String inputString = scanner.nextLine();
                    List<String> atoms = convertStringToAtoms(inputString);

                    System.out.println("Результат - список атомів:");
                    System.out.println(atoms);
                    break;
                case 3:
                    addFamilyToDatabase(scanner);
                    break;
                case 4:
                    findFamiliesWithTwoChildren();
                    break;
                case 5:
                    findWomenWithTwoChildren();
                    break;
                case 6:
                    printAllPeople();
                    break;
                case 7:
                    printWorkingWives();
                    break;
                case 8:
                    printPeopleWithExperienceLessThan10();
                    break;
                case 9:
                    System.out.println("Дякую за використання програми. Вихід...");
                    break;
                default:
                    System.out.println("Невірний вибір опції. Спробуйте ще раз.");
                    break;
            }
        } while (choice != 9);
    }

    private static void readIntegersToList(Scanner scanner) {
        List<Integer> numbers = new ArrayList<>();
        int num;

        System.out.println("Введіть цілі числа (для завершення введення введіть 0):");
        do {
            num = scanner.nextInt();
            if (num != 0) {
                numbers.add(num);
            }
        } while (num != 0);

        System.out.println("Ваш список чисел:");
        System.out.println(numbers);
    }

    private static void convertStringToCharList(Scanner scanner) {
        System.out.println("Введіть рядок:");
        String inputString = scanner.next();
        List<Character> charList = new ArrayList<>();

        for (char c : inputString.toCharArray()) {
            charList.add(c);
        }

        System.out.println("Результат - список символів:");
        System.out.println(charList);
    }

    private static void addFamilyToDatabase(Scanner scanner) {
        System.out.println("Введіть дані для нової сім'ї:");

        Person husband = readPersonFromUser("чоловіка", scanner);
        Person wife = readPersonFromUser("дружину", scanner);

        System.out.println("Введіть кількість дітей у цій сім'ї:");
        int numOfChildren = scanner.nextInt();
        List<Person> children = new ArrayList<>();

        for (int i = 0; i < numOfChildren; ++i) {
            children.add(readPersonFromUser("дитину " + (i + 1), scanner));
        }

        Family newFamily = new Family(husband, wife, children);
        families.add(newFamily);
    }

    private static List<String> convertStringToAtoms(String inputString) {
        return Arrays.asList(inputString.split(" "));
    }

    private static Person readPersonFromUser(String role, Scanner scanner) {
        System.out.println("Ім'я " + role + ":");
        String firstName = scanner.next();

        System.out.println("Прізвище " + role + ":");
        String lastName = scanner.next();

        System.out.println("День народження " + role + " (формат: день місяць рік):");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();

        Date birthDate = new Date(day, month, year);

        System.out.println("Чи " + role + " працює? (y/n)");
        boolean isEmployed = scanner.next().equalsIgnoreCase("y");

        Job job = isEmployed ? readJobFromUser(scanner) : new Job(false, "", 0);

        return new Person(firstName, lastName, birthDate, job);
    }

    private static Job readJobFromUser(Scanner scanner) {
        System.out.println("Назва професії:");
        String profession = scanner.next();

        System.out.println("Кількість років досвіду:");
        int experience = scanner.nextInt();

        return new Job(true, profession, experience);
    }

    private static void findFamiliesWithTwoChildren() {
        System.out.println("Родини з двома дітьми:");
        for (Family family : families) {
            if (family.getNumberOfChildren() == 2) {
                System.out.println(family);
            }
        }
    }

    private static void findWomenWithTwoChildren() {
        System.out.println("Жінки з двома дітьми:");
        for (Family family : families) {
            if (family.getWife().isFemale() && family.getNumberOfChildren() == 2) {
                System.out.println(family.getWife().firstName + " " + family.getWife().lastName);
            }
        }
    }

    private static void printAllPeople() {
        System.out.println("Всі люди в базі даних:");
        for (Family family : families) {
            System.out.println(family.husband);
            System.out.println(family.wife);
            for (Person child : family.children) {
                System.out.println(child);
            }
        }
    }

    private static void printWorkingWives() {
        System.out.println("Дружини, які працюють:");
        for (Family family : families) {
            if (family.getWife().job.employed) {
                System.out.println(family.getWife().firstName + " " + family.getWife().lastName);
            }
        }
    }

    private static void printPeopleWithExperienceLessThan10() {
        System.out.println("Люди з меншим стажем роботи (менше 10 років):");
        for (Family family : families) {
            if (family.husband.job.employed && family.husband.job.experience < 10) {
                System.out.println(family.husband.firstName + " " + family.husband.lastName);
            }
            if (family.getWife().job.employed && family.getWife().job.experience < 10) {
                System.out.println(family.getWife().firstName + " " + family.getWife().lastName);
            }
        }
    }
}