public class Main {

    public static void main(String[] args) {
        // Исходный массив
        int[] array = new int[] {5, 6, 3, 2, 5, 1, 4, 9};

        // Вывод массива до сортировки
        System.out.println("Исходный массив:");
        printArray(array);

        // Сортировка массива
        sortArray(array);

        // Вывод массива после сортировки
        System.out.println("Отсортированный массив:");;
        printArray(array);

    }

    public static void sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int a = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = a;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        for (int e : array) {
            System.out.print(e + " ");
        }
        System.out.print("\n");
    }
}
