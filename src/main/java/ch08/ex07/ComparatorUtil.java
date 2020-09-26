package ch08.ex07;

import java.util.Comparator;

public class ComparatorUtil {

    private ComparatorUtil() {

    }

    public static <T extends Comparable<? super T>>
            Comparator<T> reversedNullsLast() {
        return Comparator.nullsLast(Comparator.reverseOrder());
    }
}