package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsScrollTo implements WebElementMethodImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        ScrollTo scrollToFunction = ReflectionUtils.newInstance(ScrollTo.class);
        JsOperation<SingleResult<Void>> operation = JsOperation.single(element.getLocatorChain(), scrollToFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
