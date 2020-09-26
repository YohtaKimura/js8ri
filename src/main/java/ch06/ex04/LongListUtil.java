package ch06.ex04;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.LongAccumulator;

public class LongListUtil {
    private LongListUtil() {
        // non-instantiable
    }
    public static Long max(List<Long> list) {
        Objects.requireNonNull(list, "list is null");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }

        LongAccumulator la = new LongAccumulator(
                (left, right) -> {
                    return left > right ? left : right;
                }, Long.MIN_VALUE);

        list.parallelStream().forEach(e -> la.accumulate(e));
        return la.longValue();
    }

    public static Long min(List<Long> list) {
        Objects.requireNonNull(list, "list is null");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }

        LongAccumulator la = new LongAccumulator(
                (left, right) -> {
                    return left > right ? right : left;
                }, Long.MAX_VALUE);

        list.parallelStream().forEach(e -> la.accumulate(e));
        return la.longValue();
    }
}
