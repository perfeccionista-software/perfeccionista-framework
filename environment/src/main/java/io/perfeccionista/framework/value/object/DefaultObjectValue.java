package io.perfeccionista.framework.value.object;

import io.perfeccionista.framework.value.checker.ObjectChecker;
import io.perfeccionista.framework.value.transformer.object.ActualObjectValueTransformer;
import io.perfeccionista.framework.value.transformer.object.ExpectedObjectValueTransformer;
import io.perfeccionista.framework.value.transformer.object.ObjectValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public class DefaultObjectValue implements ObjectValue {

    protected final ObjectChecker objectChecker;

    protected boolean inverse = false;

    public DefaultObjectValue(ObjectChecker objectChecker) {
        this.objectChecker = objectChecker;
    }

    @Override
    public ObjectValue transformExpected(UnaryOperator<Object> transformFunction) {
        objectChecker.addTransformer((ExpectedObjectValueTransformer) transformFunction::apply);
        return this;
    }

    @Override
    public ObjectValue transformActual(UnaryOperator<Object> transformFunction) {
        objectChecker.addTransformer((ActualObjectValueTransformer) transformFunction::apply);
        return this;
    }

    @Override
    public ObjectValue addTransformer(@NotNull ObjectValueTransformer transformer) {
        objectChecker.addTransformer(transformer);
        return this;
    }

    @Override
    public ObjectValue inverse() {
        inverse = true;
        return this;
    }

    @Override
    public @NotNull Object get() {
        return objectChecker.getProcessedExpected();
    }

    @Override
    public boolean check(@Nullable Object actual) {
        if (actual == null) {
            return false;
        }
        objectChecker.setActual(actual);
        if (inverse) {
            return !objectChecker.check();
        }
        return objectChecker.check();
    }

    @Override
    public String toString() {
        return objectChecker.getComparisonDescription();
    }

}