package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.java.ParameterType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.cucumber.parameters.CssPropertyParameter;
import io.perfeccionista.framework.cucumber.parameters.CssPropertyParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DimensionsParameter;
import io.perfeccionista.framework.cucumber.parameters.DimensionsParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.CallParameter;
import io.perfeccionista.framework.cucumber.parameters.CallParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ColorParameter;
import io.perfeccionista.framework.cucumber.parameters.ColorParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameter;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DateTimeFormatParameter;
import io.perfeccionista.framework.cucumber.parameters.DateTimeFormatParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.DurationParameter;
import io.perfeccionista.framework.cucumber.parameters.DurationParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.FixtureParameter;
import io.perfeccionista.framework.cucumber.parameters.FixtureParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.LocationParameter;
import io.perfeccionista.framework.cucumber.parameters.LocationParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.NamedMethodParameter;
import io.perfeccionista.framework.cucumber.parameters.NamedMethodParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameter;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ScreenshotParameter;
import io.perfeccionista.framework.cucumber.parameters.ScreenshotParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueNumberParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueRuNumberParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebBrowserParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBrowserParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebElementActionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementActionParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebElementInteractionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementInteractionParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementComponentParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementPropertyParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebListValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.WebListValueExtractorParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebPageParameter;
import io.perfeccionista.framework.cucumber.parameters.WebPageParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameterImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.intellij.lang.annotations.Language;

public class RuParametersDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    // Mark default cucumber group as Non-capturing.
    private static final String IGNORE_OUTER_GROUP = "?:";

    @Language("RegExp")
    private static final String BRACKETS_PATTERN = IGNORE_OUTER_GROUP + "\\(([^(\\\\]*(?:\\\\.[^)\\\\]*)*)\\)";

    @Language("RegExp")
    private static final String SQUARE_BRACKETS_PATTERN = IGNORE_OUTER_GROUP + "\\[(.+)]";

    @Language("RegExp")
    private static final String DOUBLE_QUOTE_STRING_PATTERN = IGNORE_OUTER_GROUP + "\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"";

    // TODO support single quoted arguments
    @Language("RegExp")
    private static final String SINGLE_QUOTE_STRING_PATTERN = IGNORE_OUTER_GROUP + "'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'";

    // Parameter definition

    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public CallParameter callName(String callName) {
        return new CallParameterImpl();
    }

    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public FixtureParameter<?, ?> fixtureName(String fixtureName) {
        return new FixtureParameterImpl<>(getEnvironment(), fixtureName);
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
    public LocationParameter location(String value) {
        return new LocationParameterImpl(getEnvironment(), value);
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
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public CssPropertyParameter cssProperty(String value) {
        return new CssPropertyParameterImpl(value);
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
    public DurationParameter duration(String value) {
        return new DurationParameterImpl(value);
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @param value
     * @return
     */
    @ParameterType(BRACKETS_PATTERN)
    public NamedMethodParameter namedMethod(String value) {
        return new NamedMethodParameterImpl(value);
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
        return new SortDirectionParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public StringComparatorTypeParameter comparatorType(String value) {
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
        return new ValueRuNumberParameter(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение для {@link StringValue}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueStringParameter stringValue(String value) {
        return new ValueStringParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value - имя конфигурации запускаемого браузера
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebBrowserParameter webBrowser(String value) {
        return new WebBrowserParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение, указывающее местоположение элемента в текущем контексте поиска
     * @param <T> - тип искомого элемента
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public <T extends WebChildElement> WebBlockElementParameter<T> webBlockElement(String value) {
        return new WebBlockElementParameterImpl<>(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebElementActionParameter webElementAction(String value) {
        return new WebElementActionParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebElementInteractionParameter webElementInteraction(String value) {
        return new WebElementInteractionParameterImpl(value);
    }

    /**
     *
     * @param value - строковое выражение, указывающее местоположение элемента в текущем контексте поиска
     * @param <T> - тип искомого элемента
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public <T extends WebChildElement> WebElementParameter<T> webElement(String value) {
        return new WebElementParameterImpl<>(getEnvironment(), value);
    }

    /**
     *
     * @param value - строковое выражение, для имени проверяемого состояния
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebElementComponentParameter webElementComponent(String value) {
        return new WebElementComponentParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebElementPropertyParameter webElementProperty(String value) {
        return new WebElementPropertyParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebListValueExtractorParameter webListValueExtractor(String value) {
       return new WebListValueExtractorParameterImpl(getEnvironment(), value);
    }

    /**
     * @param value - имя активной страницы
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebPageParameter webPage(String value) {
        return new WebPageParameterImpl(getEnvironment(), value);
    }

    /**
     *
      * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebTableValueExtractorParameter webTableValueExtractor(String value) {
        return new WebTableValueExtractorParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebTableColumnParameter webTableColumn(String value) {
        return new WebTableColumnParameterImpl(value);
    }

}
