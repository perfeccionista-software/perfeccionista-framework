package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetText implements WebElementMethodImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<SingleResult<String>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), getTextFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
