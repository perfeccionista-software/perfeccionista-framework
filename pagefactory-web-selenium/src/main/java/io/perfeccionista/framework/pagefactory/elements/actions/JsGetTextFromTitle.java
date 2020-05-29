package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetTextFromTitle implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetAttribute getTextFunction = ReflectionUtils.newInstance(GetAttribute.class, "title");
        JsOperation<String> operation = JsOperation.of(element.getLocatorChainTo(TEXT), getTextFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}