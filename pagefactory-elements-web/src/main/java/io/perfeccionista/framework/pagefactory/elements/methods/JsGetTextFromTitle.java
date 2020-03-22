package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetAttribute;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetTextFromTitle implements WebElementMethodImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetAttribute getTextFunction = ReflectionUtils.newInstance(GetAttribute.class);
        getTextFunction.setAttribute("title");
        JsOperation<SingleResult<String>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), getTextFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}