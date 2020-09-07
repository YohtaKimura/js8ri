package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LazyLogger {

    private final Logger logger;

    public LazyLogger(Logger logger) {
        this.logger = logger;
        setLogLevel(Level.ALL);
    }
    public void setLogLevel(Level level) {
        logger.setLevel(level);
    }
    public void logIf(Level level, BooleanSupplier condition, Supplier<String> message) {
        if (logger.isLoggable(level)) {
            if (condition.getAsBoolean()) {
                logger.log(level, message.get());
            }
        }
    }
}
