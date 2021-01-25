package io.perfeccionista.framework.pagefactory.elements.states.base;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface MobileElementStateExtractor {

    MobileElementOperation<Boolean> getOperation(@NotNull MobileChildElementBase element, Optional<MobileLocatorHolder> locatorHolder);

}
