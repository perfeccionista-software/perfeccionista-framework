package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_PROPERTY_VALUE_METHOD;

/**
 * TODO JavaDoc
 */
public interface MobileElementPropertyAvailable extends MobileChildElementBase {

    Optional<MobileElementPropertyHolder> getProperty(String propertyName);

    @MobileMappedElementAction(GET_PROPERTY_VALUE_METHOD)
    @Nullable String getPropertyValue(@NotNull String propertyName);

    @AssertMethodType
    MobileChildElementBase should(@NotNull MobileElementPropertyAvailableMatcher matcher);

}
