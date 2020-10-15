package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class WebElementParameterImpl<T> implements WebElementParameter<T> {

    private final Environment environment;
    private final String rawInput;

    public WebElementParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public T findSingle() {
        List<T> elements = find().collect(toList());
        if (elements.size() == 0) {
            // TODO: no element found
        } else if (elements.size() > 1) {
            // TODO: more than one element found
        }
        return elements.get(0);
    }

    @Override
    public <R extends WebChildElement> R findSingle(Class<R> elementClass) {
        return (R) findSingle();
    }

    @Override
    public Stream<T> find() {
        return environment.getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .getSearchContexts()
                .map(webParentElement -> webParentElement.getElementRegistry().getElementByPath(rawInput))
                // TODO: Тут нужно еще дополнительно проверять что извлекаемый элемент соответствует типу
                .map(webChildElement -> (T) webChildElement);
    }

    @Override
    public <R extends WebChildElement> Stream<R> find(Class<R> elementClass) {
        return find()
                // TODO: Тут нужно еще дополнительно проверять что извлекаемый элемент соответствует типу
                .map(webChildElement -> (R) webChildElement);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
