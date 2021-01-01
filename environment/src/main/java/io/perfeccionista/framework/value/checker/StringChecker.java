package io.perfeccionista.framework.value.checker;

import org.jetbrains.annotations.NotNull;

public interface StringChecker extends Checker<String> {

    @Override
    @NotNull String getExpected();

    @Override
    @NotNull String getProcessedExpected();

    void setProcessExpectedStatement(boolean processExpectedStatement);

    boolean isProcessExpectedStatement();

}
