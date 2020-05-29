package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;

public interface ChildElement<T extends ParentElement<?>> extends Element,
        IsPresentAvailable, IsDisplayedAvailable,
        GetDimensionsAvailable, GetLocationAvailable, GetScreenshotAvailable {

    T getParent();

    boolean isRequired();

}
