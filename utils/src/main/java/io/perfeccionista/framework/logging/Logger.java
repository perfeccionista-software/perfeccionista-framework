package io.perfeccionista.framework.logging;

import java.util.function.Supplier;

public interface Logger {

    void error(Supplier<String> messageSupplier);
    void error(Supplier<String> messageSupplier, Throwable throwable);

    void warn(Supplier<String> messageSupplier);
    void warn(Supplier<String> messageSupplier, Throwable throwable);

    void info(Supplier<String> messageSupplier);
    void info(Supplier<String> messageSupplier, Throwable throwable);

    void config(Supplier<String> messageSupplier);
    void config(Supplier<String> messageSupplier, Throwable throwable);

    void debug(Supplier<String> messageSupplier);
    void debug(Supplier<String> messageSupplier, Throwable throwable);

    void trace(Supplier<String> messageSupplier);
    void trace(Supplier<String> messageSupplier, Throwable throwable);

}
