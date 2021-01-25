package io.perfeccionista.framework.pagefactory.elements.interactions.base;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

public interface MobileElementInteractionImplementation {

    MobileChildElement execute(@NotNull MobileChildElement sourceElement, @NotNull MobileChildElement targetElement, Object... args);

}
