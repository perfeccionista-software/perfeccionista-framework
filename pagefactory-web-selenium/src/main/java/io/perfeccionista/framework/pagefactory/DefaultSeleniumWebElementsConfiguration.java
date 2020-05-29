package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;

import java.util.Map;

public class DefaultSeleniumWebElementsConfiguration implements WebElementsConfiguration {

    @Override
    public <T extends Element, I extends AbstractChildElement<?>> Map<Class<T>, Class<I>> getElementImplementations() {
        return null;
    }

}
