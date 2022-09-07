import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }

        @Override
        public String toString() {
            return name + " (" + id + ')';
        }
    }

    private static final Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia")
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        // Выводим на экран исходные данные
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.getId() + " - " + person.getName());
        }

        // Убираем дубликаты, сортируем по идентификатору и группируем данные по имени
        Map<String, List<Person>> dataDeduplicatedSortedAndGrouped =
                getDuplicateFilteredSortedByIdAndGroupedByName(RAW_DATA);

        // Выводим на экран обработанные данные как в образце до задания 1
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        for (Map.Entry<String, List<Person>> entry : dataDeduplicatedSortedAndGrouped.entrySet()) {
            System.out.println(entry.getKey() + ':');
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(i + 1 + " - " + entry.getValue().get(i));
            }
        }

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
        */

        // Убираем дубликаты, группируем данные по имени, подсчитываем количество людей в группе.
        // Сортировка по идентификатору для получения необходимого вывода не нужна
        Map<String, Long> dataDeduplicatedAndGrouped = getDuplicateFilteredAndGroupedByNameWithCounting(RAW_DATA);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task1. Duplicate filtered, grouped by name, sorted by name:");
        System.out.println();

        for (Map.Entry<String, Long> entry : dataDeduplicatedAndGrouped.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("Value: " + entry.getValue());
        }

        /*
        Task2
            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        // Находим нужную пару
        int[] array = new int[]{3, 4, 2, 7};
        int sumOfPair = 10;
        int[] pair = getPair(array, sumOfPair);

        // Выводим результат не экран
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task 2. Got the pair of numbers from array with the sum equals 10:");
        System.out.println();
        System.out.println(Arrays.toString(array) + ", " + sumOfPair +
                " -> " + Arrays.toString(pair));

        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        // Выводим на экран результат работы функции нечеткого поиска
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task 3. The result of running the fuzzySearch function:");
        System.out.println();

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        System.out.println(fuzzySearch("lw", "cartwheel"));
    }

    public static Map<String, List<Person>> getDuplicateFilteredSortedByIdAndGroupedByName(Person[] rawData) {
        return Arrays.stream(rawData)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName));
    }

    public static Map<String, Long> getDuplicateFilteredAndGroupedByNameWithCounting(Person[] rawData) {
        return Arrays.stream(rawData)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
    }

    public static int[] getPair(int[] array, int sumOfPair) {
        // Получаем отсортированный массив
        int[] sortedArray = Arrays.stream(array)
                .sorted()
                .toArray();
        // Устанавливаем указатели вначале и в конце массива
        int startIndex = 0;
        int endIndex = sortedArray.length - 1;
        // Проходим по элементам с двух строн, пока указатели не будут указывать на одно и то же число
        while (sortedArray[startIndex] != sortedArray[endIndex]) {
            // Если сумма равна искомой, возвращаем числа
            // Если меньше, увеличиваем начальный указатель, иначе уменьшаем конечный указатель
            if (sortedArray[startIndex] + sortedArray[endIndex] == sumOfPair) {
                return new int[]{sortedArray[startIndex], sortedArray[endIndex]};
            } else if (sortedArray[startIndex] + sortedArray[endIndex] < sumOfPair) {
                startIndex++;
            } else {
                endIndex--;
            }
        }
        return null;
    }

    public static boolean fuzzySearch(String s1, String s2) {
        // Преобразуем исходные строки в массивы символов
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // Указываем начальные индексы массивов
        int index1 = 0;
        int index2 = 0;

        // Проверяем может ли первый массив войти во второй по размеру
        if (chars1.length > chars2.length) {
            return false;
        }

        while (index2 < chars2.length) {
            // если символ из первого массива имеется во втором, переходим к следующему символу
            if (chars1[index1] == chars2[index2]) {
                index1++;
            }
            // если в первом массиве символы закончились, значит он нечетко содержится во втором массиве
            if (index1 == chars1.length) {
                return true;
            }
            index2++;
        }
        // Если мы прошли по всему второму массиву, а первый не закончился, значит он не содержится во втором
        return false;
    }
}
