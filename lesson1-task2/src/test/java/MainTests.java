import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTests {

    @Test
    public void getMaxValueTest() {
        int[] arrayToSort = new int[] {5, 6, 3, 2, 5, 1, 4, 9};
        int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 9};
        Main.sortArray(arrayToSort);
        Assertions.assertArrayEquals(expected, arrayToSort);
    }
}
