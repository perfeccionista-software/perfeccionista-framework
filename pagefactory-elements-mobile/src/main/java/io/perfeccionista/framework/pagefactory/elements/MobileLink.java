package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.TapAvailable;

public interface MobileLink extends MobileChildElement,
        TapAvailable, GetTextAvailable {
}
