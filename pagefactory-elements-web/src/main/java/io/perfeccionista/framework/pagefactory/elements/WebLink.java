package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;

public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {
}
