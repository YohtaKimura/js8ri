package ch01.ex09;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
    default void voidforEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach((e) -> {
            if(filter.test(e)){
                action.accept(e);
            }
        });
    }
}
