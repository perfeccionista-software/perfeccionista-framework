package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;

public interface ClickAvailable extends ElementMethodAvailable {

    OperationResult<Void> click();

}
