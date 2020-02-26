package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SendKeysAvailable extends ElementMethodAvailable {

    OperationResult<Void> sendKeys(CharSequence... keys);

}
