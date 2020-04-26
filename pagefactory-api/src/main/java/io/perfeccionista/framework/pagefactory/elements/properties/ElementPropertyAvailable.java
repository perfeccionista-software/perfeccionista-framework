package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;

/**
 * TODO JavaDoc
 */
public interface ElementPropertyAvailable<T extends ElementPropertyHolder> extends Element {

    Optional<T> getProperty(String propertyName);

    @MappedElementAction(GET_PROPERTY_VALUE_METHOD)
    String getPropertyValue(String propertyName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_PROPERTY_VALUE_METHOD)
    ElementPropertyAvailable<T> shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_PROPERTY_NUMBER_METHOD)
    ElementPropertyAvailable<T> shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD)
    ElementPropertyAvailable<T> shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD)
    ElementPropertyAvailable<T> shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);

}
