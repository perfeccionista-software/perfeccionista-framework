package io.perfeccionista.framework.value;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
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
import io.perfeccionista.framework.value.checker.number.DoubleNotEqualNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerGreaterNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerLessNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.number.IntegerNotEqualsNumberChecker;
import io.perfeccionista.framework.value.checker.object.ObjectEqualsChecker;
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
import io.perfeccionista.framework.value.object.DefaultObjectValue;
import io.perfeccionista.framework.value.object.ObjectValue;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import io.perfeccionista.framework.value.string.DefaultStringValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@DefaultServiceConfiguration(DefaultValueServiceConfiguration.class)
public class ValueService implements Service {

    protected Environment environment;
    protected ServiceConfiguration configuration = null;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    // Inverse

    public ObjectValue not(@NotNull ObjectValue objectValue) {
        return objectValue.inverse();
    }

    public StringValue not(@NotNull StringValue stringValue) {
        return stringValue.inverse();
    }

    // Object

    public ObjectValue objectEquals(@NotNull Object expected) {
        return new DefaultObjectValue(new ObjectEqualsChecker(environment, expected));
    }

    public Object objectProcess(@NotNull String expression) {
        return new ValueExpressionProcessor(environment).processExpression(expression);
    }

    // String

    public StringValue stringEmpty() {
        return new DefaultStringValue(new StringEmptyValueChecker(environment));
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

    public StringValue stringContainsAll(@NotNull String... expectedValues) {
        return new DefaultStringValue(new StringContainsAllChecker(environment, new HashSet<>(Arrays.asList(expectedValues))));
    }

    public StringValue stringContainsAny(@NotNull String... expectedValues) {
        return new DefaultStringValue(new StringContainsAnyChecker(environment, new HashSet<>(Arrays.asList(expectedValues))));
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
        return new DefaultStringValue(new StringLengthChecker(environment, expectedLength));
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
        return new DefaultDoubleValue(new DoubleNotEqualNumberChecker(expected));
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
