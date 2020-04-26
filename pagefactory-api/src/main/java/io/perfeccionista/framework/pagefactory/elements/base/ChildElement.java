package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.methods.GetBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.components.ElementComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;

public interface ChildElement<T extends ParentElement<?>> extends Element,
        ElementComponentAvailable, IsPresentAvailable, IsDisplayedAvailable,
        GetBoundsAvailable, GetScreenshotAvailable {

    T getParent();

    boolean isRequired();

}
