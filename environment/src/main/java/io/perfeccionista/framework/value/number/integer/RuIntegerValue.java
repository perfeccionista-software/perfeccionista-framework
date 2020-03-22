package io.perfeccionista.framework.value.number.integer;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueDeclaration;
import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.number.NumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerGreaterNumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerLessNumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integer.checker.IntegerNotEqualsNumberChecker;

public class RuIntegerValue extends AbstractNumberValue<Integer> {

    private RuIntegerValue(Environment environment, String expected) {
        super(environment, expected);
    }

    public static RuIntegerValue of(@NotNull Environment environment, @NotNull String rawExpected) {
        return new RuIntegerValue(environment, rawExpected);
    }

    @Override
    protected @NotNull NumberChecker<Integer> resolveChecker(ValueDeclaration valueDeclaration) {
        if (valueDeclaration.getValueCondition().isEmpty()) {
            return new IntegerEqualsNumberChecker();
        } else if (valueDeclaration.getValueCondition().get().contains("больше или равно")) {
            return new IntegerGreaterOrEqualsNumberChecker();
        } else if (valueDeclaration.getValueCondition().get().contains("больше")) {
            return new IntegerGreaterNumberChecker();
        } else if (valueDeclaration.getValueCondition().get().contains("меньше или равно")) {
            return new IntegerLessOrEqualsNumberChecker();
        } else if (valueDeclaration.getValueCondition().get().contains("меньше")) {
            return new IntegerLessNumberChecker();
        } else if (valueDeclaration.getValueCondition().get().contains("не равно")) {
            return new IntegerNotEqualsNumberChecker();
        }
        return new IntegerEqualsNumberChecker();
    }

    @Override
    public @NotNull Integer get() {
        if (numberChecker.isProcessExpectedStatement()) {
            Object processedValue = processExpectedStatement(valueDeclaration.getValueExpression());
            processedExpected = Integer.parseInt(processedValue.toString());
        } else {
            processedExpected = Integer.parseInt(valueDeclaration.getValueExpression());
        }
        return processedExpected;
    }

    @Override
    public boolean check(@NotNull Integer actual) {
        this.actual = actual;
        if (numberChecker.isProcessExpectedStatement()) {
            Object processedValue = processExpectedStatement(valueDeclaration.getValueExpression());
            processedExpected = Integer.parseInt(processedValue.toString());
        } else {
            processedExpected = Integer.parseInt(valueDeclaration.getValueExpression());
        }
        return numberChecker.check(processedExpected, actual);
    }

    @Override
    public String toString() {
        return String.format("Integer value: {locale = Ru; conditions = %s; rawExpected = '%s'}\n"
                        + "    processedExpected = '%s'\n"
                        + "               actual = '%s'",
                valueDeclaration.getValueCondition().orElse("none"), rawExpected, processedExpected, actual);
    }

}
