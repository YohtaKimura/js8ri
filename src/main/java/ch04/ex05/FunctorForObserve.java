package ch04.ex05;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctorForObserve {
    // https://stackoverflow.com/questions/28140680/transform-an-obervablevalue
    // TODO:: RUN
    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> original) {
        return new Transformer<>(f, original);
    }

    public static class Transformer<T, R> extends ObservableValueBase<R> {
        private final Function<T, R> f;
        private final ObservableValue<T> value;

        Transformer(Function<T, R> f, ObservableValue<T> value) {
            this.f = f;
            this.value = value;
            value.addListener(observable -> fireValueChangedEvent()); // invalidation
            value.addListener((observable, oldValue, newValue) -> fireValueChangedEvent()); // change
        }

        @Override
        public R getValue() {
            return f.apply(value.getValue());
        }
    }

    public static class TransFormerForTwoValues<T, U, R> extends ObservableValueBase<R> {
        private final BiFunction<T, U, R> f;
        private final ObservableValue<T> value1;
        private final ObservableValue<U> value2;

        TransFormerForTwoValues(BiFunction<T, U, R> f, ObservableValue<T> value1, ObservableValue<U> value2) {
            this.f = f;
            this.value1 = value1;
            this.value2 = value2;
            value1.addListener(observable -> fireValueChangedEvent());
            value1.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
            value2.addListener(observable -> fireValueChangedEvent());
            value2.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
        }

        @Override
        public R getValue() {
            return f.apply(value1.getValue(), value2.getValue());
        }
    }
}
