package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.value.checker.StringChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.function.UnaryOperator;

public class DefaultStringValue implements StringValue {

    protected final StringChecker stringChecker;
    protected final Deque<UnaryOperator<String>> actualValueTransformers = new ArrayDeque<>();

    protected String rawActual;
    protected String processedActual;
    protected boolean inverse = false;

    public DefaultStringValue(StringChecker stringChecker) {
        this.stringChecker = stringChecker;
    }

    @Override
    public StringValue transformExpected(UnaryOperator<String> transformFunction) {
        stringChecker.setTransformers(Set.of(transformFunction));
        return this;
    }

    @Override
    public StringValue transformActual(UnaryOperator<String> transformFunction) {
        actualValueTransformers.clear();
        actualValueTransformers.addLast(transformFunction);
        return this;
    }

    @Override
    public StringValue withoutProcessing() {
        stringChecker.setProcessExpectedStatement(false);
        return this;
    }

    @Override
    public StringValue inverse() {
        inverse = true;
        return this;
    }

    @Override
    public @NotNull String get() {
        return stringChecker.getProcessedExpected();
    }

    @Override
    public boolean check(@Nullable String actual) {
        rawActual = actual;
        if (actual == null) {
            return false;
        }
        processedActual = actual;
        for (UnaryOperator<String> transformer : actualValueTransformers) {
            processedActual = transformer.apply(processedActual);
        }
        if (inverse) {
            return !stringChecker.check(processedActual);
        }
        return stringChecker.check(processedActual);
    }

    @Override
    public String toString() {
        return String.format("             Expected = '%s'\n"
                           + "               Actual = '%s'\n"
                           + "    processedExpected = '%s'\n"
                           + "      processedActual = '%s'\n"
                           + "String value parameters: {checker = %s; inverse = %s}",
                stringChecker.getExpected(), rawActual, stringChecker.getProcessedExpected(), processedActual,
                stringChecker.getClass().getCanonicalName(), inverse);
    }

}
