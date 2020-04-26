package io.perfeccionista.framework.value;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalLessNumberChecker;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.bigdecimalvalue.BigDecimalNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleLessNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.doublevalue.DoubleNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerLessNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.integervalue.IntegerNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringContainsAllChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringContainsAnyChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringContainsChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringContainsIgnoreCaseChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringEmptyValueChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringEndsWithChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringEqualsChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringEqualsIgnoreCaseChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringLengthChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringRegularExpressionChecker;
import io.perfeccionista.framework.value.checker.stringvalue.StringStartsWithChecker;
import io.perfeccionista.framework.value.number.DefaultBigDecimalValue;
import io.perfeccionista.framework.value.number.DefaultDoubleValue;
import io.perfeccionista.framework.value.number.DefaultIntegerValue;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import io.perfeccionista.framework.value.string.DefaultStringValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Collection;

public class DefaultValueService implements ValueService {

    protected Environment environment;
    protected ServiceConfiguration configuration = null;

    @Override
    public void init(@NotNull Environment environment, @Nullable ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    // String

    public StringValue stringEmpty() {
        return new DefaultStringValue(new StringEmptyValueChecker());
    }

    public StringValue stringEquals(@NotNull String expected) {
        return new DefaultStringValue(new StringEqualsChecker(environment, expected));
    }

    public StringValue stringEqualsIgnoreCase(@NotNull String expected) {
        return new DefaultStringValue(new StringEqualsIgnoreCaseChecker(environment, expected));
    }

    public StringValue stringContains(@NotNull String expected) {
        return new DefaultStringValue(new StringContainsChecker(environment, expected));
    }

    public StringValue stringContainsIgnoreCase(@NotNull String expected) {
        return new DefaultStringValue(new StringContainsIgnoreCaseChecker(environment, expected));
    }

    public StringValue stringContainsAll(@NotNull Collection<String> expectedValues) {
        return new DefaultStringValue(new StringContainsAllChecker(environment, expectedValues));
    }

    public StringValue stringContainsAny(@NotNull Collection<String> expectedValues) {
        return new DefaultStringValue(new StringContainsAnyChecker(environment, expectedValues));
    }

    public StringValue stringStartsWith(@NotNull String expected) {
        return new DefaultStringValue(new StringStartsWithChecker(environment, expected));
    }

    public StringValue stringEndsWith(@NotNull String expected) {
        return new DefaultStringValue(new StringEndsWithChecker(environment, expected));
    }

    public StringValue stringMatches(@Language("RegExp") @NotNull String expected) {
        return new DefaultStringValue(new StringRegularExpressionChecker(environment, expected));
    }

    public StringValue stringLength(int expectedLength) {
        return new DefaultStringValue(new StringLengthChecker(expectedLength));
    }

    public String stringProcess(@NotNull String expression) {
        return new ValueExpressionProcessor(environment).processExpression(expression).toString();
    }

    // Integer

    public NumberValue<Integer> intEquals(int expected) {
        return new DefaultIntegerValue(new IntegerEqualsNumberChecker(expected));
    }

    public NumberValue<Integer> intNotEquals(int expected) {
        return new DefaultIntegerValue(new IntegerNotEqualsNumberChecker(expected));
    }

    public NumberValue<Integer> intGreaterThan(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterNumberChecker(expected));
    }

    public NumberValue<Integer> intGreaterThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterOrEqualsNumberChecker(expected));
    }

    public NumberValue<Integer> intLessThan(int expected) {
        return new DefaultIntegerValue(new IntegerLessNumberChecker(expected));
    }

    public NumberValue<Integer> intLessThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerLessOrEqualsNumberChecker(expected));
    }

    public int intProcess(@NotNull String expression) {
        return Integer.parseInt(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

    // Double

    public NumberValue<Double> doubleEquals(double expected) {
        return new DefaultDoubleValue(new DoubleEqualsNumberChecker(expected));
    }

    public NumberValue<Double> doubleNotEquals(double expected) {
        return new DefaultDoubleValue(new DoubleNotEqualsNumberChecker(expected));
    }

    public NumberValue<Double> doubleGreaterThan(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterNumberChecker(expected));
    }

    public NumberValue<Double> doubleGreaterThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterOrEqualsNumberChecker(expected));
    }

    public NumberValue<Double> doubleLessThan(double expected) {
        return new DefaultDoubleValue(new DoubleLessNumberChecker(expected));
    }

    public NumberValue<Double> doubleLessThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleLessOrEqualsNumberChecker(expected));
    }

    public double doubleProcess(@NotNull String expression) {
        return Double.parseDouble(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

    // BigDecimal

    public NumberValue<BigDecimal> bigDecimalEquals(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalEqualsNumberChecker(expected));
    }

    public NumberValue<BigDecimal> bigDecimalNotEquals(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalNotEqualsNumberChecker(expected));
    }

    public NumberValue<BigDecimal> bigDecimalGreaterThan(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterNumberChecker(expected));
    }

    public NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterOrEqualsNumberChecker(expected));
    }

    public NumberValue<BigDecimal> bigDecimalLessThan(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessNumberChecker(expected));
    }

    public NumberValue<BigDecimal> bigDecimalLessThanOrEqual(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessOrEqualsNumberChecker(expected));
    }

    public BigDecimal bigDecimalProcess(@NotNull String expression) {
        return new BigDecimal(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

}
