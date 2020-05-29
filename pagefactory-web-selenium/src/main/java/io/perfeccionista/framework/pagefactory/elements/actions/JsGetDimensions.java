package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.jsfunction.GetDimensions;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetDimensions implements WebElementActionImplementation<Dimensions> {

    @Override
    public Dimensions execute(WebChildElement element, Object... args) {
        GetDimensions getDimensionsFunction = ReflectionUtils.newInstance(GetDimensions.class);
        JsOperation<Dimensions> operation = JsOperation.of(element.getLocatorChainTo(TEXT), getDimensionsFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
