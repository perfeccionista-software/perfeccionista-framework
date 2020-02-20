package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateAvailable;

public interface ChildElement extends Element,
        ElementPropertyAvailable, ElementStateAvailable, IsDisplayedAvailable,
        HoverToAvailable, ScrollToAvailable, GetBoundsAvailable, GetScreenshotAvailable {

    <T> ElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    ParentElement getParent();

    boolean isRequired();

}
