package io.perfeccionista.framework.value.number.bigdecimalvalue;

import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class RuBigDecimalValue extends AbstractNumberValue<BigDecimal> {

    public RuBigDecimalValue(NumberChecker<BigDecimal> numberChecker) {
        super(numberChecker);
    }

    @Override
    public boolean checkString(@NotNull String actual) {
        // TODO: Implement
        return false;
    }

    //    public RuBigDecimalValue(Environment environment, String expected) {
//        super(environment, expected);
//    }
//
//    @Override
//    protected @NotNull NumberChecker<BigDecimal> resolveChecker(ValueDeclaration valueDeclaration) {
//        if (valueDeclaration.getValueCondition().isEmpty()) {
//            return new BigDecimalEqualsNumberChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("больше или равно")) {
//            return new BigDecimalGreaterOrEqualsNumberChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("больше")) {
//            return new BigDecimalGreaterNumberChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("меньше или равно")) {
//            return new BigDecimalLessOrEqualsNumberChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("меньше")) {
//            return new BigDecimalLessNumberChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("не равно")) {
//            return new BigDecimalNotEqualsNumberChecker();
//        }
//        return new BigDecimalEqualsNumberChecker();
//    }
//
//    @Override
//    public @NotNull BigDecimal get() {
//        if (numberChecker.isProcessExpectedStatement()) {
//            Object processedValue = processExpectedStatement(valueDeclaration.getValueExpression());
//            processedExpected = new BigDecimal(processedValue.toString());
//        } else {
//            processedExpected = new BigDecimal(valueDeclaration.getValueExpression());
//        }
//        return processedExpected;
//    }
//
//    @Override
//    public boolean check(@NotNull BigDecimal actual) {
//        this.actual = actual;
//        if (numberChecker.isProcessExpectedStatement()) {
//            Object processedValue = processExpectedStatement(valueDeclaration.getValueExpression());
//            processedExpected = new BigDecimal(processedValue.toString());
//        } else {
//            processedExpected = new BigDecimal(valueDeclaration.getValueExpression());
//        }
//        return numberChecker.check(processedExpected, actual);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("BigDecimal value: {locale = Ru; conditions = %s; rawExpected = '%s'}\n"
//                        + "    processedExpected = '%s'\n"
//                        + "               actual = '%s'",
//                valueDeclaration.getValueCondition().orElse("none"), rawExpected, processedExpected.toString(), actual.toString());
//    }

}

