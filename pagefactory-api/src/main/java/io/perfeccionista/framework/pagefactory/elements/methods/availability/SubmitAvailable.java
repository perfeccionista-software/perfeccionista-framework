package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SubmitAvailable extends ElementMethodAvailable {

    OperationResult<Void> submit();

}
