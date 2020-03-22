package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetBounds;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetBounds implements WebElementMethodImplementation<Bounds> {

    @Override
    public Bounds execute(WebChildElement element, Object... args) {
        GetBounds getBoundsFunction = ReflectionUtils.newInstance(GetBounds.class);
        JsOperation<SingleResult<Bounds>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), getBoundsFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
