public class Main {
    private static long randomSeed = System.nanoTime() % 997;

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        fillArrayWithRandoms(array);

        System.out.println("Ваш массив:");
        printArray(array);

        System.out.println("Минимальное значение: " + getMinValue(array));
        System.out.println("Максимальное значение: " + getMaxValue(array));
        System.out.println("Среднее значение: " + getAverageValue(array));
    }

    public static void fillArrayWithRandoms(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = getRandomInt();
            }
        }
    }

    public static int getRandomInt() {
        randomSeed = (7 * randomSeed + 17) % 51;
        return (int)randomSeed;
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int e : row) {
                System.out.print(e + " ");
            }
            System.out.print("\n");
        }
    }

    public static int getMaxValue(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    public static int getMinValue(int[][] array) {
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }

    public static double getAverageValue(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
        return (double) sum / (array.length * array[0].length);
    }
}
