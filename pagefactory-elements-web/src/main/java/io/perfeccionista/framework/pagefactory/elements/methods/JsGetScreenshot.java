package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetScreenshot implements WebElementMethodImplementation<Screenshot> {

    @Override
    public Screenshot execute(WebChildElement element, Object... args) {
        GetScreenshot getScreenshotFunction = ReflectionUtils.newInstance(GetScreenshot.class);
        JsOperation<SingleResult<Screenshot>> operation = JsOperation.single(element.getLocatorChain(), getScreenshotFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
