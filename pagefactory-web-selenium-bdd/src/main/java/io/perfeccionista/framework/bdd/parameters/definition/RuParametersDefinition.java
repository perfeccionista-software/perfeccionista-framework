package io.perfeccionista.framework.bdd.parameters.definition;

import io.cucumber.java.ParameterType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.DimensionsParameter;
import io.perfeccionista.framework.bdd.parameters.DimensionsParameterImpl;
import io.perfeccionista.framework.bdd.parameters.CallParameter;
import io.perfeccionista.framework.bdd.parameters.CallParameterImpl;
import io.perfeccionista.framework.bdd.parameters.ColorParameter;
import io.perfeccionista.framework.bdd.parameters.ColorParameterImpl;
import io.perfeccionista.framework.bdd.parameters.DataStorageParameter;
import io.perfeccionista.framework.bdd.parameters.DataStorageParameterImpl;
import io.perfeccionista.framework.bdd.parameters.DateTimeFormatParameter;
import io.perfeccionista.framework.bdd.parameters.DateTimeFormatParameterImpl;
import io.perfeccionista.framework.bdd.parameters.DurationParameter;
import io.perfeccionista.framework.bdd.parameters.DurationParameterImpl;
import io.perfeccionista.framework.bdd.parameters.FixtureParameter;
import io.perfeccionista.framework.bdd.parameters.FixtureParameterImpl;
import io.perfeccionista.framework.bdd.parameters.NamedMethodParameter;
import io.perfeccionista.framework.bdd.parameters.NamedMethodParameterImpl;
import io.perfeccionista.framework.bdd.parameters.ScenarioNameParameter;
import io.perfeccionista.framework.bdd.parameters.ScenarioNameParameterImpl;
import io.perfeccionista.framework.bdd.parameters.ScreenshotParameter;
import io.perfeccionista.framework.bdd.parameters.ScreenshotParameterImpl;
import io.perfeccionista.framework.bdd.parameters.SortDirectionParameter;
import io.perfeccionista.framework.bdd.parameters.SortDirectionParameterImpl;
import io.perfeccionista.framework.bdd.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.bdd.parameters.StringComparatorTypeParameterImpl;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueRuIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueRuNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueRuStringParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebBlockElementParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebBrowserParameter;
import io.perfeccionista.framework.bdd.parameters.WebBrowserParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebElementActionParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementActionParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebElementInteractionParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementInteractionParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebListValueExtractorParameter;
import io.perfeccionista.framework.bdd.parameters.WebListValueExtractorParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebPageParameter;
import io.perfeccionista.framework.bdd.parameters.WebPageParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameterImpl;
import io.perfeccionista.framework.bdd.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableValueExtractorParameterImpl;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.intellij.lang.annotations.Language;

public class RuParametersDefinition implements EnvironmentAvailable {

    private final Environment environment;

    public RuParametersDefinition(Environment environment) {
        this.environment = environment;
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
    public FixtureParameter<?> fixtureName(String fixtureName) {
        return new FixtureParameterImpl<>();
    }






    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public DimensionsParameter dimensions(String value) {
        return new DimensionsParameterImpl(value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ColorParameter color(String value) {
        return new ColorParameterImpl(value);
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
        return new DataStorageParameterImpl<>(environment, value);
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
        return new ScenarioNameParameterImpl(environment, value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ScreenshotParameter screenshot(String value) {
        return new ScreenshotParameterImpl(environment, value);
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
        return new StringComparatorTypeParameterImpl(environment, value);
    }

    /**
     *
     * @param value - строковое выражение для {@link io.perfeccionista.framework.value.number.NumberValue<Integer>}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueIntegerParameter integerValue(String value) {
        return new ValueRuIntegerParameter(environment, value);
    }

    /**
     *
     * @param value - строковое выражение для {@link io.perfeccionista.framework.value.number.NumberValue<?>}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueNumberParameter numberValue(String value) {
        return new ValueRuNumberParameter(environment, value);
    }

    /**
     *
     * @param value - строковое выражение для {@link io.perfeccionista.framework.value.string.StringValue}
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public ValueStringParameter stringValue(String value) {
        return new ValueRuStringParameter(environment, value);
    }

    /**
     *
     * @param value - имя конфигурации запускаемого браузера
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebBrowserParameter webBrowser(String value) {
        return new WebBrowserParameterImpl(environment, value);
    }

    /**
     *
     * @param value - строковое выражение, указывающее местоположение элемента в текущем контексте поиска
     * @param <T> - тип искомого элемента
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public <T extends WebChildElement> WebBlockElementParameter<T> webBlockElement(String value) {
        return new WebBlockElementParameterImpl<>(environment, value);
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
    public <T extends Element> WebElementParameter<T> webElement(String value) {
        return new WebElementParameterImpl<>(environment, value);
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
       return new WebListValueExtractorParameterImpl(environment, value);
    }

    /**
     * @param value - имя активной страницы
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebPageParameter webPage(String value) {
        return new WebPageParameterImpl(environment, value);
    }

    /**
     *
      * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebTableValueExtractorParameter webTableValueExtractor(String value) {
        return new WebTableValueExtractorParameterImpl(environment, value);
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
