package io.perfeccionista.framework.value;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.object.ObjectValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Values {

    private Values() {
    }

    // Inverse

    public static ObjectValue not(@NotNull ObjectValue objectValue) {
        return objectValue.inverse();
    }

    public static StringValue not(@NotNull StringValue stringValue) {
        return stringValue.inverse();
    }

    // Objects

    public static ObjectValue objectEquals(@NotNull Object expected) {
        return getValueService().objectEquals(expected);
    }

    public static ObjectValue objectSame(@NotNull Object expected) {
        return getValueService().objectSame(expected);
    }

    public static Object objectProcess(@NotNull String expression) {
        return getValueService().objectProcess(expression);
    }

    // String

    public static StringValue stringEmpty() {
        return getValueService().stringEmpty();
    }

    public static StringValue stringEquals(@NotNull String expected) {
        return getValueService().stringEquals(expected);
    }

    public static StringValue stringEqualsIgnoreCase(@NotNull String expected) {
        return getValueService().stringEqualsIgnoreCase(expected);
    }

    public static StringValue stringContains(@NotNull String expected) {
        return getValueService().stringContains(expected);
    }

    public static StringValue stringContainsIgnoreCase(@NotNull String expected) {
        return getValueService().stringContainsIgnoreCase(expected);
    }

    public static StringValue stringContainsAll(@NotNull String... expectedValues) {
        return getValueService().stringContainsAll(expectedValues);
    }

    public static StringValue stringContainsAny(@NotNull String... expectedValues) {
        return getValueService().stringContainsAny(expectedValues);
    }

    public static StringValue stringStartsWith(@NotNull String expected) {
        return getValueService().stringStartsWith(expected);
    }

    public static StringValue stringEndsWith(@NotNull String expected) {
        return getValueService().stringEndsWith(expected);
    }

    public static StringValue stringMatches(@Language("RegExp") @NotNull String expected) {
        return getValueService().stringMatches(expected);
    }

    public static StringValue stringLength(int expectedLength) {
        return getValueService().stringLength(expectedLength);
    }

    public static String stringProcess(@NotNull String expression) {
        return getValueService().stringProcess(expression);
    }

    // Integer

    public static NumberValue<Integer> intEquals(int expected) {
        return getValueService().intEquals(expected);
    }

    public static NumberValue<Integer> intNotEquals(int expected) {
        return getValueService().intNotEquals(expected);
    }

    public static NumberValue<Integer> intGreaterThan(int expected) {
        return getValueService().intGreaterThan(expected);
    }

    public static NumberValue<Integer> intGreaterThanOrEqual(int expected) {
        return getValueService().intGreaterThanOrEqual(expected);
    }

    public static NumberValue<Integer> intLessThan(int expected) {
        return getValueService().intLessThan(expected);
    }

    public static NumberValue<Integer> intLessThanOrEqual(int expected) {
        return getValueService().intLessThanOrEqual(expected);
    }

    public static int intProcess(@NotNull String expression) {
        return getValueService().intProcess(expression);
    }

    // Double

    public static NumberValue<Double> doubleEquals(double expected) {
        return getValueService().doubleEquals(expected);
    }

    public static NumberValue<Double> doubleNotEquals(double expected) {
        return getValueService().doubleNotEquals(expected);
    }

    public static NumberValue<Double> doubleGreaterThan(double expected) {
        return getValueService().doubleGreaterThan(expected);
    }

    public static NumberValue<Double> doubleGreaterThanOrEqual(double expected) {
        return getValueService().doubleGreaterThanOrEqual(expected);
    }

    public static NumberValue<Double> doubleLessThan(double expected) {
        return getValueService().doubleLessThan(expected);
    }

    public static NumberValue<Double> doubleLessThanOrEqual(double expected) {
        return getValueService().doubleLessThanOrEqual(expected);
    }

    public static double doubleProcess(@NotNull String expression) {
        return getValueService().doubleProcess(expression);
    }

    // BigDecimal

    public static NumberValue<BigDecimal> bigDecimalEquals(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalEquals(expected);
    }

    public static NumberValue<BigDecimal> bigDecimalNotEquals(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalNotEquals(expected);
    }

    public static NumberValue<BigDecimal> bigDecimalGreaterThan(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalGreaterThan(expected);
    }

    public static NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalGreaterThanOrEqual(expected);
    }

    public static NumberValue<BigDecimal> bigDecimalLessThan(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalLessThan(expected);
    }

    public static NumberValue<BigDecimal> bigDecimalLessThanOrEqual(@NotNull BigDecimal expected) {
        return getValueService().bigDecimalLessThanOrEqual(expected);
    }

    public static BigDecimal bigDecimalProcess(@NotNull String expression) {
        return getValueService().bigDecimalProcess(expression);
    }

    // Get service

    private static ValueService getValueService() {
        return Environment.getForCurrentThread().getService(ValueService.class);
    }

}
