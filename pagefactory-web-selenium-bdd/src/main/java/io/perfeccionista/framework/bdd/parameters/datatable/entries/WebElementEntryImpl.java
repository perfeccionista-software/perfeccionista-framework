package io.perfeccionista.framework.bdd.parameters.datatable.entries;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameterImpl;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.Map;

public class WebElementEntryImpl<T extends Element> implements WebElementEntry<T> {

    private final WebElementParameter<T> webElementParameter;
    private final String rawInput;

    public WebElementEntryImpl(Environment environment, Map<String, String> entry) {
        verify(entry);
        this.webElementParameter = new WebElementParameterImpl<>(environment, entry.get("webElement"));
        this.rawInput = mapToString(entry);
    }

    @Override
    public WebElementParameter<T> get() {
        return webElementParameter;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

    @Override
    public Map<String, String> verify(Map<String, String> entry) {
        // TODO: Implement verification
        return entry;
    }

}
