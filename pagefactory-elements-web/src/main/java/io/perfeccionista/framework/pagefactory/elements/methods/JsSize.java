package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetSize;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsSize implements WebElementMethodImplementation<Integer> {

    @Override
    public OperationResult<Integer> execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        return OperationResult.of(() -> {
            GetSize getSizeFunction = ReflectionUtils.newInstance(GetSize.class);
            JsOperation<SingleResult<Integer>> operation = JsOperation.single(element.getLocatorChainTo(component), getSizeFunction);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
