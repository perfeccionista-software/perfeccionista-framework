package io.perfeccionista.framework.value.number;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueDeclaration;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;

public abstract class AbstractNumberValue<T extends Number> implements NumberValue<T> {

    protected final String rawExpected;
    protected final ValueDeclaration valueDeclaration;
    protected final NumberChecker<T> numberChecker;
    protected final Environment environment;

    protected T processedExpected;
    protected T actual;

    protected AbstractNumberValue(Environment environment, String expected) {
        this.environment = environment;
        this.rawExpected = expected;
        this.valueDeclaration = parseRawExpected(expected);
        this.numberChecker = resolveChecker(valueDeclaration);
    }

    protected Object processExpectedStatement(String valueExpression) {
        return new ValueExpressionProcessor(environment).processExpression(valueExpression);
    }

    protected abstract @NotNull NumberChecker<T> resolveChecker(ValueDeclaration valueDeclaration);



}
