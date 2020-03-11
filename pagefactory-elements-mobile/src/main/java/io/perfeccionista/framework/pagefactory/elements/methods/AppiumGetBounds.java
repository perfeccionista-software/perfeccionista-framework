package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class AppiumGetBounds implements MobileElementMethodImplementation<Bounds> {

    @Override
    public OperationResult<Bounds> execute(MobileChildElement element, Object... args) {
        // TODO: Implement
        return OperationResult.of(() -> {return null;});
    }

}
