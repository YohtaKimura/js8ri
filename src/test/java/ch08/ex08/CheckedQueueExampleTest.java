package ch08.ex08;

import ch08.ex07.ComparatorUtil;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedQueueExampleTest {
    @Test
    public void testComparator() {
        // Prepare
        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int next = random.nextInt();
            list.add(String.valueOf(next));
        }

        for (int i = 0; i < 5; i++) {
            list.add(null);
        }

        String[] array1 = list.toArray(new String[0]);
        String[] array2 = list.toArray(new String[0]);

        // Action
        Arrays.sort(array1, Comparator.<String>nullsFirst(Comparator.naturalOrder()).reversed());
        Arrays.sort(array2, ComparatorUtil.reversedNullsLast());

        // Check
        assertTrue(Arrays.equals(array1, array2));
    }
}
