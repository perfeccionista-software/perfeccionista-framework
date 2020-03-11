package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class AppiumSize implements MobileElementMethodImplementation<Integer> {

    @Override
    public OperationResult<Integer> execute(MobileChildElement element, Object... args) {
        String component = (String) args[0];
        return OperationResult.of(() -> {
            LocatorChain locatorChain = element.getLocatorChainTo(component);
            return element.findElements(locatorChain).getItems().size();
        });
    }

}
