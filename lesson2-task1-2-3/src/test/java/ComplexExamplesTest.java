import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ComplexExamplesTest {

    private static final ComplexExamples.Person[] RAW_DATA = new ComplexExamples.Person[]{
            new ComplexExamples.Person(0, "Harry"),
            new ComplexExamples.Person(0, "Harry"),
            new ComplexExamples.Person(1, "Harry"),
            new ComplexExamples.Person(2, "Harry"),
            new ComplexExamples.Person(3, "Emily"),
            new ComplexExamples.Person(4, "Jack"),
            new ComplexExamples.Person(4, "Jack"),
            new ComplexExamples.Person(5, "Amelia"),
            new ComplexExamples.Person(5, "Amelia"),
            new ComplexExamples.Person(6, "Amelia"),
            new ComplexExamples.Person(7, "Amelia"),
            new ComplexExamples.Person(8, "Amelia")
    };

    @Test
    public void getDuplicateFilteredSortedByIdAndGroupedByNameTest() {
        Map<String, List<ComplexExamples.Person>> expected = new HashMap<>();
        expected.put("Amelia", Arrays.asList(
                new ComplexExamples.Person(5, "Amelia"),
                new ComplexExamples.Person(6, "Amelia"),
                new ComplexExamples.Person(7, "Amelia"),
                new ComplexExamples.Person(8, "Amelia")
        ));
        expected.put("Emily", List.of(
                new ComplexExamples.Person(3, "Emily")
        ));
        expected.put("Harry", Arrays.asList(
                new ComplexExamples.Person(0, "Harry"),
                new ComplexExamples.Person(1, "Harry"),
                new ComplexExamples.Person(2, "Harry")
        ));
        expected.put("Jack", List.of(
                new ComplexExamples.Person(4, "Jack")
        ));
        Map<String, List<ComplexExamples.Person>> result =
                ComplexExamples.getDuplicateFilteredSortedByIdAndGroupedByName(RAW_DATA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getDuplicateFilteredAndGroupedByNameWithCountingTest() {
        Map<String, Long> expected = new HashMap<>();
        expected.put("Amelia", 4L);
        expected.put("Emily", 1L);
        expected.put("Harry", 3L);
        expected.put("Jack", 1L);
        Map<String, Long> result = ComplexExamples.getDuplicateFilteredAndGroupedByNameWithCounting(RAW_DATA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getPairTest() {
        int[] array = new int[]{3, 4, 2, 7};
        int sumOfPair = 10;
        int[] expected = new int[]{3, 7};
        int[] result = ComplexExamples.getPair(array, sumOfPair);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void getPairReturnNullTest() {
        int[] array = new int[]{7, 6, 6, 5};
        int sumOfPair = 10;
        int[] result = ComplexExamples.getPair(array, sumOfPair);
        Assertions.assertNull(result);
    }

    @ParameterizedTest
    @MethodSource ("stringProviderTrue")
    public void fuzzySearchTrueTest(String s1, String s2) {
        boolean expected = true;
        boolean result = ComplexExamples.fuzzySearch(s1, s2);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource ("stringProviderFalse")
    public void fuzzySearchFalseTest(String s1, String s2) {
        boolean expected = false;
        boolean result = ComplexExamples.fuzzySearch(s1, s2);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> stringProviderTrue() {
        return Stream.of(
                arguments("car", "ca6$$#_rtwheel"),
                arguments("cwhl", "cartwheel"),
                arguments("cwhee", "cartwheel"),
                arguments("cartwheel", "cartwheel")
        );
    }

    private static Stream<Arguments> stringProviderFalse() {
        return Stream.of(
                arguments("cwheeel", "cartwheel"),
                arguments("lw", "cartwheel"),
                arguments("cwhesl", "cartwheels"),
                arguments("cartwheell", "cartwheel")
        );
    }
}
