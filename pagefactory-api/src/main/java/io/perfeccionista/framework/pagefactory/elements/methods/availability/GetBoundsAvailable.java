package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface GetBoundsAvailable extends ElementMethodAvailable {

    OperationResult<Bounds> getBounds();

}
