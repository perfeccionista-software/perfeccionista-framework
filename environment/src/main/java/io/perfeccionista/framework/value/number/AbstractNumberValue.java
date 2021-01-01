package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.checker.NumberChecker;
import io.perfeccionista.framework.value.transformer.number.ActualNumberValueTransformer;
import io.perfeccionista.framework.value.transformer.number.ExpectedNumberValueTransformer;
import io.perfeccionista.framework.value.transformer.number.NumberValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public abstract class AbstractNumberValue<T extends Number> implements NumberValue<T> {

    protected final NumberChecker<T> numberChecker;

    public AbstractNumberValue(@NotNull NumberChecker<T> numberChecker) {
        this.numberChecker = numberChecker;
    }

    @Override
    public AbstractNumberValue<T> transformExpected(@NotNull UnaryOperator<T> transformFunction) {
        numberChecker.addTransformer((ExpectedNumberValueTransformer<T>) transformFunction::apply);
        return this;
    }

    @Override
    public AbstractNumberValue<T> transformActual(@NotNull UnaryOperator<T> transformFunction) {
        numberChecker.addTransformer((ActualNumberValueTransformer<T>) transformFunction::apply);
        return this;
    }

    @Override
    public NumberValue<T> addTransformer(@NotNull NumberValueTransformer<T> transformer) {
        numberChecker.addTransformer(transformer);
        return this;
    }

    @Override
    public @NotNull T get() {
        return numberChecker.getProcessedExpected();
    }

    @Override
    public boolean check(@Nullable T actual) {
        if (actual == null) {
            return false;
        }
        numberChecker.setActual(actual);
        return numberChecker.check();
    }

    @Override
    public String toString() {
        return numberChecker.getComparisonDescription();
    }

}
