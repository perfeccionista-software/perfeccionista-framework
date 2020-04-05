package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
public interface WebButton extends WebLink {

    @Override
    WebButton click();

    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    @Override
    WebButton scrollTo();

    @Override
    WebButton shouldBeDisplayed();

    @Override
    WebButton shouldNotBeDisplayed();

    @Override
    WebButton shouldHaveBounds(Bounds bounds);

    @Override
    WebButton shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebButton shouldHaveText(StringValue stringValue);

    @Override
    WebButton shouldLooksLike(Screenshot screenshot);

    @Override
    WebButton stateShouldBeDisplayed(String stateName);

}
