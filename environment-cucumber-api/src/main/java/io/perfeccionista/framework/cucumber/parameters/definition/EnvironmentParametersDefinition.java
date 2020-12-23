package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.java.ParameterType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.cucumber.parameters.ColorParameter;
import io.perfeccionista.framework.cucumber.parameters.ColorParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameter;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DateTimeFormatParameter;
import io.perfeccionista.framework.cucumber.parameters.DateTimeFormatParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DimensionsParameter;
import io.perfeccionista.framework.cucumber.parameters.DimensionsParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DurationParameter;
import io.perfeccionista.framework.cucumber.parameters.DurationParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.FixtureParameter;
import io.perfeccionista.framework.cucumber.parameters.FixtureParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.LocationParameter;
import io.perfeccionista.framework.cucumber.parameters.LocationParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameter;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ScreenshotParameter;
import io.perfeccionista.framework.cucumber.parameters.ScreenshotParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.StepNameParameter;
import io.perfeccionista.framework.cucumber.parameters.StepNameParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueNumberParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueNumberParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameterImpl;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;

// TODO: Вынести привязку к имплементации параметров в конфигурацию
public class EnvironmentParametersDefinition implements EnvironmentAvailable {

    public static final String IGNORE_OUTER_GROUP = "?:";
    @Language("RegExp")
    public static final String BRACKETS_PATTERN = IGNORE_OUTER_GROUP + "\\(([^(\\\\]*(?:\\\\.[^)\\\\]*)*)\\)";
    @Language("RegExp")
    public static final String SQUARE_BRACKETS_PATTERN = IGNORE_OUTER_GROUP + "\\[(.+)]";
    @Language("RegExp")
    public static final String DOUBLE_QUOTE_STRING_PATTERN = IGNORE_OUTER_GROUP + "\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"";
    @Language("RegExp")
    public static final String SINGLE_QUOTE_STRING_PATTERN = IGNORE_OUTER_GROUP + "'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'";

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ColorParameter color(String value) {
        return new ColorParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public <K, V> DataStorageParameter<K, V> dataStorage(String value) {
        return new DataStorageParameterImpl<>(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(SQUARE_BRACKETS_PATTERN)
    public DateTimeFormatParameter dateTimeFormat(String value) {
        return new DateTimeFormatParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public DimensionsParameter dimensions(String value) {
        return new DimensionsParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public DurationParameter duration(String value) {
        return new DurationParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public FixtureParameter<?, ?> fixture(String value) {
        return new FixtureParameterImpl<>(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public LocationParameter location(String value) {
        return new LocationParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ScenarioNameParameter scenarioName(String value) {
        return new ScenarioNameParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ScreenshotParameter screenshot(String value) {
        return new ScreenshotParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public SortDirectionParameter sortDirection(String value) {
        return new SortDirectionParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public StepNameParameter stepName(String value) {
        return new StepNameParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public StringComparatorTypeParameter stringComparatorType(String value) {
        return new StringComparatorTypeParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение для {@link NumberValue <Integer>}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueIntegerParameter integerValue(String value) {
        return new ValueIntegerParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение для {@link NumberValue <?>}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueNumberParameter numberValue(String value) {
        return new ValueNumberParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение для {@link StringValue}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueStringParameter stringValue(String value) {
        return new ValueStringParameterImpl(getEnvironment(), value);
    }

}
