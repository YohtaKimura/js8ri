package ch03.ex17;

import java.util.Objects;
import java.util.function.Consumer;
// 柴田さんの回答を見て初めて意味が分かったやつ
public class ex17 {

    private ex17() {
        // Non-instantiable
    }

    public static void doInParallelAsync(Runnable first, Runnable second,
            Consumer<Throwable> handler) {
        Objects.requireNonNull(first, "first is null");
        Objects.requireNonNull(second, "second is null");
        Objects.requireNonNull(handler, "handler is null");

        Thread t1 = new Thread(
                () -> {
                    try {
                        first.run();
                    } catch (Throwable e) {
                        handler.accept(e);
                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    try {
                        second.run();
                    } catch (Throwable e) {
                        handler.accept(e);
                    }
                }
        );

        t1.start();
        t2.start();

    }

}

