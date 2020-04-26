package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetSize;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsSize implements WebElementActionImplementation<Integer> {

    @Override
    public Integer execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        GetSize getSizeFunction = ReflectionUtils.newInstance(GetSize.class);
        JsOperation<SingleResult<Integer>> operation = JsOperation.single(element.getLocatorChainTo(component), getSizeFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
