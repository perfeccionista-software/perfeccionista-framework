package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetText;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetText implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<String> operation = JsOperation.of(element.getLocatorChainTo(TEXT), getTextFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
