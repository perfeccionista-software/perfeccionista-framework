package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.js.JsFunction;

import java.util.Map;

public class DefaultWebElementsConfiguration implements WebElementsConfiguration {

    @Override
    public <T extends Element, I extends AbstractChildElement<?>> Map<Class<T>, Class<I>> elementImplementations() {
        return null;
    }

    @Override
    public Map<Class<? extends JsFunction<?, ?>>, String> jsFunctionScripts() {
        return null;
    }
}
