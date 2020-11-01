package io.perfeccionista.framework.value;

import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public interface ValueService extends Service {

    // TODO: Object
    // ObjectValue objectEquals(String dataSourceExpression) {}

    // Object

    Object objectProcess(@NotNull String expression);

    // String

    StringValue stringEmpty();

    StringValue stringEquals(@NotNull String expected);

    StringValue stringEqualsIgnoreCase(@NotNull String expected);

    StringValue stringContains(@NotNull String expected);

    StringValue stringContainsIgnoreCase(@NotNull String expected);

    StringValue stringContainsAll(@NotNull String... expectedValues);

    StringValue stringContainsAny(@NotNull String... expectedValues);

    StringValue stringStartsWith(@NotNull String expected);

    StringValue stringEndsWith(@NotNull String expected);

    StringValue stringMatches(@Language("RegExp") @NotNull String expected);

    StringValue stringLength(int expectedLength);

    String stringProcess(@NotNull String expression);

    // Integer

    NumberValue<Integer> intEquals(int expected);

    NumberValue<Integer> intNotEquals(int expected);

    NumberValue<Integer> intGreaterThan(int expected);

    NumberValue<Integer> intGreaterThanOrEqual(int expected);

    NumberValue<Integer> intLessThan(int expected);

    NumberValue<Integer> intLessThanOrEqual(int expected);

    int intProcess(@NotNull String expression);

    // Double

    NumberValue<Double> doubleEquals(double expected);

    NumberValue<Double> doubleNotEquals(double expected);

    NumberValue<Double> doubleGreaterThan(double expected);

    NumberValue<Double> doubleGreaterThanOrEqual(double expected);

    NumberValue<Double> doubleLessThan(double expected);

    NumberValue<Double> doubleLessThanOrEqual(double expected);

    double doubleProcess(@NotNull String expression);

    // BigDecimal

    NumberValue<BigDecimal> bigDecimalEquals(@NotNull BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalNotEquals(@NotNull BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalGreaterThan(@NotNull BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(@NotNull BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalLessThan(@NotNull BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalLessThanOrEqual(@NotNull BigDecimal expected);

    BigDecimal bigDecimalProcess(@NotNull String expression);

}
