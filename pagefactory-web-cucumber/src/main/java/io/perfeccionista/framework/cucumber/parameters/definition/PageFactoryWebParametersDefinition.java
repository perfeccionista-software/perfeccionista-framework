package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.java.ParameterType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.cucumber.parameters.CssPropertyParameter;
import io.perfeccionista.framework.cucumber.parameters.CssPropertyParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.NamedMethodParameter;
import io.perfeccionista.framework.cucumber.parameters.NamedMethodParameterImpl;
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
import io.perfeccionista.framework.cucumber.parameters.WebListBlockValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.WebListBlockValueExtractorParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebPageParameter;
import io.perfeccionista.framework.cucumber.parameters.WebPageParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebRadioButtonValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.WebRadioButtonValueExtractorParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameterImpl;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameterImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import static io.perfeccionista.framework.cucumber.parameters.definition.EnvironmentParametersDefinition.BRACKETS_PATTERN;
import static io.perfeccionista.framework.cucumber.parameters.definition.EnvironmentParametersDefinition.DOUBLE_QUOTE_STRING_PATTERN;

public class PageFactoryWebParametersDefinition implements EnvironmentAvailable {

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
    public CssPropertyParameter cssProperty(String value) {
        return new CssPropertyParameterImpl(value);
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
        return new WebBlockElementParameterImpl<>(value);
    }

    /**
     *
     * @param value - строковое выражение, указывающее местоположение элемента в текущем контексте поиска
     * @param <T> - тип искомого элемента
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public <T extends WebChildElement> WebElementParameter<T> webElement(String value) {
        return new WebElementParameterImpl<>(value);
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
    public WebListBlockValueExtractorParameter webListValueExtractor(String value) {
        return new WebListBlockValueExtractorParameterImpl(getEnvironment(), value);
    }

    /**
     *
     * @param value
     * @return
     */
    @ParameterType(DOUBLE_QUOTE_STRING_PATTERN)
    public WebRadioButtonValueExtractorParameter webRadioButtonValueExtractor(String value) {
        return new WebRadioButtonValueExtractorParameterImpl(getEnvironment(), value);
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
    public WebTableColumnParameter webTableColumn(String value) {
        return new WebTableColumnParameterImpl(value);
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

}
