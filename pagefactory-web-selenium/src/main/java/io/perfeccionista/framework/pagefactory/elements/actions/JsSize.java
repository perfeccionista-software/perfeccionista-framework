package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetSize;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsSize implements WebElementActionImplementation<Integer> {

    @Override
    public Integer execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        GetSize getSizeFunction = ReflectionUtils.newInstance(GetSize.class);
        JsOperation<Integer> operation = JsOperation.of(element.getLocatorChainTo(component), getSizeFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
