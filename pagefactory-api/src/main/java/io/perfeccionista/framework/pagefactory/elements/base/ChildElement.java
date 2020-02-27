package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateAvailable;

public interface ChildElement<T extends ParentElement<?>> extends Element,
        ElementPropertyAvailable, ElementStateAvailable, IsDisplayedAvailable,
        GetBoundsAvailable, GetScreenshotAvailable {

    T getParent();

    boolean isRequired();

}
