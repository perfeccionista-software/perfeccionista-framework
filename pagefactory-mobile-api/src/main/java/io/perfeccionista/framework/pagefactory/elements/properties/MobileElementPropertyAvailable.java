package io.perfeccionista.framework.pagefactory.elements.properties;


import java.util.Optional;


public interface MobileElementPropertyAvailable <T extends MobileElementPropertyHolder> {

    Optional<T> getProperty(String propertyName);

//    @MappedElementAction(GET_PROPERTY_VALUE_METHOD)
//    String getPropertyValue(String propertyName);
//
//    @AssertMethodType
//    @MappedElementAction(SHOULD_HAVE_PROPERTY_VALUE_METHOD)
//    MobileElementPropertyAvailable<T> shouldHavePropertyValue(String propertyName, StringValue stringValue);
//
//    @AssertMethodType
//    @MappedElementAction(SHOULD_HAVE_PROPERTY_NUMBER_METHOD)
//    MobileElementPropertyAvailable<T> shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);
//
//    @AssertMethodType
//    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD)
//    MobileElementPropertyAvailable<T> shouldNotHavePropertyValue(String propertyName, StringValue stringValue);
//
//    @AssertMethodType
//    @MappedElementAction(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD)
//    MobileElementPropertyAvailable<T> shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);

}
