import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTests {

    @Test
    public void getMaxValueTest() {
        int[][] inputArray = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},
                {6, 7, 8, 7, 6}
        };
        int expected = 9;
        int rename = Main.getMaxValue(inputArray);
        Assertions.assertEquals(expected, rename);
    }

    @Test
    public void getMinValueTest() {
        int[][] inputArray = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},
                {6, 7, 8, 7, 6}
        };
        int expected = 0;
        int rename = Main.getMinValue(inputArray);
        Assertions.assertEquals(expected, rename);
    }

    @Test
    public void getAverageValueTest() {
        int[][] inputArray = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},
                {6, 7, 8, 7, 6}
        };
        double expected = 4.96;
        double rename = Main.getAverageValue(inputArray);
        Assertions.assertEquals(expected, rename);
    }
}
