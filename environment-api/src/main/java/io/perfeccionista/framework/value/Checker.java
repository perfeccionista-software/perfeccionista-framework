package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.UnaryOperator;

public interface Checker<T> {

    T getExpected();

    T getProcessedExpected();

    boolean check(@NotNull T actual);

    boolean isProcessExpectedStatement();

    void setProcessExpectedStatement(boolean processExpectedStatement);

    void setTransformers(Collection<UnaryOperator<T>> transformers);

}
