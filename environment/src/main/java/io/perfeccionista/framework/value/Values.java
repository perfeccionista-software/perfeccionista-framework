package io.perfeccionista.framework.value;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.checker.number.BigDecimalEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.BigDecimalGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.number.BigDecimalGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.BigDecimalLessNumberChecker;
import io.perfeccionista.framework.value.checker.number.BigDecimalLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.BigDecimalNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleLessNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.DoubleNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerLessNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.string.StringContainsAllChecker;
import io.perfeccionista.framework.value.checker.string.StringContainsAnyChecker;
import io.perfeccionista.framework.value.checker.string.StringContainsChecker;
import io.perfeccionista.framework.value.checker.string.StringContainsIgnoreCaseChecker;
import io.perfeccionista.framework.value.checker.string.StringEmptyValueChecker;
import io.perfeccionista.framework.value.checker.string.StringEndsWithChecker;
import io.perfeccionista.framework.value.checker.string.StringEqualsChecker;
import io.perfeccionista.framework.value.checker.string.StringEqualsIgnoreCaseChecker;
import io.perfeccionista.framework.value.checker.string.StringLengthChecker;
import io.perfeccionista.framework.value.checker.string.StringRegularExpressionChecker;
import io.perfeccionista.framework.value.checker.string.StringStartsWithChecker;
import io.perfeccionista.framework.value.number.DefaultBigDecimalValue;
import io.perfeccionista.framework.value.number.DefaultDoubleValue;
import io.perfeccionista.framework.value.number.DefaultIntegerValue;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import io.perfeccionista.framework.value.string.DefaultStringValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public class Values {

    private Values() {
    }

    // String

    public static StringValue stringEmpty() {
        return new DefaultStringValue(new StringEmptyValueChecker(Environment.getCurrent()));
    }

    public static StringValue stringEquals(@NotNull String expected) {
        return new DefaultStringValue(new StringEqualsChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringEqualsIgnoreCase(@NotNull String expected) {
        return new DefaultStringValue(new StringEqualsIgnoreCaseChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringContains(@NotNull String expected) {
        return new DefaultStringValue(new StringContainsChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringContainsIgnoreCase(@NotNull String expected) {
        return new DefaultStringValue(new StringContainsIgnoreCaseChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringContainsAll(@NotNull String... expectedValues) {
        return new DefaultStringValue(new StringContainsAllChecker(Environment.getCurrent(), Set.of(expectedValues)));
    }

    public static StringValue stringContainsAny(@NotNull String... expectedValues) {
        return new DefaultStringValue(new StringContainsAnyChecker(Environment.getCurrent(), Set.of(expectedValues)));
    }

    public static StringValue stringStartsWith(@NotNull String expected) {
        return new DefaultStringValue(new StringStartsWithChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringEndsWith(@NotNull String expected) {
        return new DefaultStringValue(new StringEndsWithChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringMatches(@Language("RegExp") @NotNull String expected) {
        return new DefaultStringValue(new StringRegularExpressionChecker(Environment.getCurrent(), expected));
    }

    public static StringValue stringLength(int expectedLength) {
        return new DefaultStringValue(new StringLengthChecker(Environment.getCurrent(), expectedLength));
    }

    public static String stringProcess(@NotNull String expression) {
        return new ValueExpressionProcessor(Environment.getCurrent()).processExpression(expression).toString();
    }

    // Integer

    public static NumberValue<Integer> intEquals(int expected) {
        return new DefaultIntegerValue(new IntegerEqualsNumberChecker(expected));
    }

    public static NumberValue<Integer> intNotEquals(int expected) {
        return new DefaultIntegerValue(new IntegerNotEqualsNumberChecker(expected));
    }

    public static NumberValue<Integer> intGreaterThan(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterNumberChecker(expected));
    }

    public static NumberValue<Integer> intGreaterThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterOrEqualsNumberChecker(expected));
    }

    public static NumberValue<Integer> intLessThan(int expected) {
        return new DefaultIntegerValue(new IntegerLessNumberChecker(expected));
    }

    public static NumberValue<Integer> intLessThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerLessOrEqualsNumberChecker(expected));
    }

    public static int intProcess(@NotNull String expression) {
        return Integer.parseInt(new ValueExpressionProcessor(Environment.getCurrent()).processExpression(expression).toString());
    }

    // Double

    public static NumberValue<Double> doubleEquals(double expected) {
        return new DefaultDoubleValue(new DoubleEqualsNumberChecker(expected));
    }

    public static NumberValue<Double> doubleNotEquals(double expected) {
        return new DefaultDoubleValue(new DoubleNotEqualsNumberChecker(expected));
    }

    public static NumberValue<Double> doubleGreaterThan(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterNumberChecker(expected));
    }

    public static NumberValue<Double> doubleGreaterThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterOrEqualsNumberChecker(expected));
    }

    public static NumberValue<Double> doubleLessThan(double expected) {
        return new DefaultDoubleValue(new DoubleLessNumberChecker(expected));
    }

    public static NumberValue<Double> doubleLessThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleLessOrEqualsNumberChecker(expected));
    }

    public static double doubleProcess(@NotNull String expression) {
        return Double.parseDouble(new ValueExpressionProcessor(Environment.getCurrent()).processExpression(expression).toString());
    }

    // BigDecimal

    public static NumberValue<BigDecimal> bigDecimalEquals(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalEqualsNumberChecker(expected));
    }

    public static NumberValue<BigDecimal> bigDecimalNotEquals(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalNotEqualsNumberChecker(expected));
    }

    public static NumberValue<BigDecimal> bigDecimalGreaterThan(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterNumberChecker(expected));
    }

    public static NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterOrEqualsNumberChecker(expected));
    }

    public static NumberValue<BigDecimal> bigDecimalLessThan(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessNumberChecker(expected));
    }

    public static NumberValue<BigDecimal> bigDecimalLessThanOrEqual(@NotNull BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessOrEqualsNumberChecker(expected));
    }

    public static BigDecimal bigDecimalProcess(@NotNull String expression) {
        return new BigDecimal(new ValueExpressionProcessor(Environment.getCurrent()).processExpression(expression).toString());
    }

}
