package io.perfeccionista.framework.value;

import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.math.BigDecimal;
import java.util.Collection;

public interface ValueService extends Service {

    // String

    StringValue stringEmpty();

    StringValue stringEquals(String expected);

    StringValue stringEqualsIgnoreCase(String expected);

    StringValue stringContains(String expected);

    StringValue stringContainsIgnoreCase(String expected);

    StringValue stringContainsAll(Collection<String> expectedValues);

    StringValue stringContainsAny(Collection<String> expectedValues);

    StringValue stringStartsWith(String expected);

    StringValue stringEndsWith(String expected);

    StringValue stringMatches(String expectedRegexp);

    StringValue stringLength(int expectedLength);

    String stringProcess(String expected);

    // Integer

    NumberValue<Integer> intEquals(int expected);

    NumberValue<Integer> intNotEquals(int expected);

    NumberValue<Integer> intGreaterThan(int expected);

    NumberValue<Integer> intGreaterThanOrEqual(int expected);

    NumberValue<Integer> intLessThan(int expected);

    NumberValue<Integer> intLessThanOrEqual(int expected);

    int intProcess(String expected);

    // Double

    NumberValue<Double> doubleEquals(double expected);

    NumberValue<Double> doubleNotEquals(double expected);

    NumberValue<Double> doubleGreaterThan(double expected);

    NumberValue<Double> doubleGreaterThanOrEqual(double expected);

    NumberValue<Double> doubleLessThan(double expected);

    NumberValue<Double> doubleLessThanOrEqual(double expected);

    double doubleProcess(String expected);

    // BigDecimal

    NumberValue<BigDecimal> bigDecimalEquals(BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalNotEquals(BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalGreaterThan(BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalLessThan(BigDecimal expected);

    NumberValue<BigDecimal> bigDecimalLessThanOrEqual(BigDecimal expected);

    BigDecimal bigDecimalProcess(String expected);

}
