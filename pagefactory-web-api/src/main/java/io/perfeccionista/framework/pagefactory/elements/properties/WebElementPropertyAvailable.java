package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;

/**
 * TODO JavaDoc
 */
public interface WebElementPropertyAvailable<T extends WebElementPropertyHolder> extends WebLocatorChainAvailable {

    Optional<T> getProperty(String propertyName);

    @MappedElementAction(GET_PROPERTY_VALUE_METHOD)
    @Nullable String getPropertyValue(@NotNull String propertyName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_PROPERTY_VALUE_METHOD)
    WebChildElement shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_PROPERTY_NUMBER_METHOD)
    WebChildElement shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD)
    WebChildElement shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD)
    WebChildElement shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
