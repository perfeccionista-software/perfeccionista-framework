package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetAttribute;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetTextFromTitle implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetAttribute getTextFunction = ReflectionUtils.newInstance(GetAttribute.class);
        getTextFunction.setAttribute("title");
        JsOperation<SingleResult<String>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), getTextFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}