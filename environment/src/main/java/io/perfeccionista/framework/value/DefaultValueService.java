package io.perfeccionista.framework.value;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.number.bigdecimalvalue.DefaultBigDecimalValue;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalEqualsNumberChecker;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalGreaterNumberChecker;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalLessNumberChecker;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.bigdecimalvalue.checker.BigDecimalNotEqualsNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.DefaultDoubleValue;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleEqualsNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleGreaterNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleLessNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.doublevalue.checker.DoubleNotEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.DefaultIntegerValue;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerGreaterNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerGreaterOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerLessNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerLessOrEqualsNumberChecker;
import io.perfeccionista.framework.value.number.integervalue.checker.IntegerNotEqualsNumberChecker;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import io.perfeccionista.framework.value.string.DefaultStringValue;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.checker.StringContainsAllChecker;
import io.perfeccionista.framework.value.string.checker.StringContainsAnyChecker;
import io.perfeccionista.framework.value.string.checker.StringContainsChecker;
import io.perfeccionista.framework.value.string.checker.StringContainsIgnoreCaseChecker;
import io.perfeccionista.framework.value.string.checker.StringEmptyValueChecker;
import io.perfeccionista.framework.value.string.checker.StringEndsWithChecker;
import io.perfeccionista.framework.value.string.checker.StringEqualsChecker;
import io.perfeccionista.framework.value.string.checker.StringEqualsIgnoreCaseChecker;
import io.perfeccionista.framework.value.string.checker.StringLengthChecker;
import io.perfeccionista.framework.value.string.checker.StringRegularExpressionChecker;
import io.perfeccionista.framework.value.string.checker.StringStartsWithChecker;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Collection;

public class DefaultValueService implements ValueService {

    private Environment environment;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
    }

    // String

    @Override
    public StringValue stringEmpty() {
        return new DefaultStringValue(new StringEmptyValueChecker());
    }

    @Override
    public StringValue stringEquals(String expected) {
        return new DefaultStringValue(new StringEqualsChecker(environment, expected));
    }

    @Override
    public StringValue stringEqualsIgnoreCase(String expected) {
        return new DefaultStringValue(new StringEqualsIgnoreCaseChecker(environment, expected));
    }

    @Override
    public StringValue stringContains(String expected) {
        return new DefaultStringValue(new StringContainsChecker(environment, expected));
    }

    @Override
    public StringValue stringContainsIgnoreCase(String expected) {
        return new DefaultStringValue(new StringContainsIgnoreCaseChecker(environment, expected));
    }

    @Override
    public StringValue stringContainsAll(Collection<String> expectedValues) {
        return new DefaultStringValue(new StringContainsAllChecker(environment, expectedValues));
    }

    @Override
    public StringValue stringContainsAny(Collection<String> expectedValues) {
        return new DefaultStringValue(new StringContainsAnyChecker(environment, expectedValues));
    }

    @Override
    public StringValue stringStartsWith(String expected) {
        return new DefaultStringValue(new StringStartsWithChecker(environment, expected));
    }

    @Override
    public StringValue stringEndsWith(String expected) {
        return new DefaultStringValue(new StringEndsWithChecker(environment, expected));
    }

    @Override
    public StringValue stringMatches(String expected) {
        return new DefaultStringValue(new StringRegularExpressionChecker(environment, expected));
    }

    @Override
    public StringValue stringLength(int expectedLength) {
        return new DefaultStringValue(new StringLengthChecker(expectedLength));
    }

    @Override
    public String stringProcess(String expression) {
        return new ValueExpressionProcessor(environment).processExpression(expression).toString();
    }

    // Integer

    @Override
    public NumberValue<Integer> intEquals(int expected) {
        return new DefaultIntegerValue(new IntegerEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Integer> intNotEquals(int expected) {
        return new DefaultIntegerValue(new IntegerNotEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Integer> intGreaterThan(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterNumberChecker(expected));
    }

    @Override
    public NumberValue<Integer> intGreaterThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerGreaterOrEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Integer> intLessThan(int expected) {
        return new DefaultIntegerValue(new IntegerLessNumberChecker(expected));
    }

    @Override
    public NumberValue<Integer> intLessThanOrEqual(int expected) {
        return new DefaultIntegerValue(new IntegerLessOrEqualsNumberChecker(expected));
    }

    @Override
    public int intProcess(String expression) {
        return Integer.parseInt(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

    // Double

    @Override
    public NumberValue<Double> doubleEquals(double expected) {
        return new DefaultDoubleValue(new DoubleEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Double> doubleNotEquals(double expected) {
        return new DefaultDoubleValue(new DoubleNotEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Double> doubleGreaterThan(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterNumberChecker(expected));
    }

    @Override
    public NumberValue<Double> doubleGreaterThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleGreaterOrEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<Double> doubleLessThan(double expected) {
        return new DefaultDoubleValue(new DoubleLessNumberChecker(expected));
    }

    @Override
    public NumberValue<Double> doubleLessThanOrEqual(double expected) {
        return new DefaultDoubleValue(new DoubleLessOrEqualsNumberChecker(expected));
    }

    @Override
    public double doubleProcess(String expression) {
        return Double.parseDouble(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

    // BigDecimal

    @Override
    public NumberValue<BigDecimal> bigDecimalEquals(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<BigDecimal> bigDecimalNotEquals(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalNotEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<BigDecimal> bigDecimalGreaterThan(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterNumberChecker(expected));
    }

    @Override
    public NumberValue<BigDecimal> bigDecimalGreaterThanOrEqual(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalGreaterOrEqualsNumberChecker(expected));
    }

    @Override
    public NumberValue<BigDecimal> bigDecimalLessThan(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessNumberChecker(expected));
    }

    @Override
    public NumberValue<BigDecimal> bigDecimalLessThanOrEqual(BigDecimal expected) {
        return new DefaultBigDecimalValue(new BigDecimalLessOrEqualsNumberChecker(expected));
    }

    @Override
    public BigDecimal bigDecimalProcess(String expression) {
        return new BigDecimal(new ValueExpressionProcessor(environment).processExpression(expression).toString());
    }

}
