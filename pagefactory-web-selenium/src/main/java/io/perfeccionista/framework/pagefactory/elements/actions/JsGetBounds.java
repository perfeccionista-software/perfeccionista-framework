package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetBounds;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetBounds implements WebElementActionImplementation<Bounds> {

    @Override
    public Bounds execute(WebChildElement element, Object... args) {
        GetBounds getBoundsFunction = ReflectionUtils.newInstance(GetBounds.class);
        JsOperation<SingleResult<Bounds>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), getBoundsFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
