package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetScreenshot implements WebElementActionImplementation<Screenshot> {

    @Override
    public Screenshot execute(WebChildElement element, Object... args) {
        GetScreenshot getScreenshotFunction = ReflectionUtils.newInstance(GetScreenshot.class);
        JsOperation<Screenshot> operation = JsOperation.of(element.getLocatorChain(), getScreenshotFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
