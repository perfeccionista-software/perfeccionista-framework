package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.plugin.Color;

public interface GetColorAvailable extends ElementMethodAvailable {

    OperationResult<Color> getColor(String component);

}
