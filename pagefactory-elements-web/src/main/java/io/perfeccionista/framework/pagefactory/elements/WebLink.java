package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    @Override
    WebLink click();

    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    @Override
    WebLink scrollTo();

    @Override
    WebLink shouldBeDisplayed();

    @Override
    WebLink shouldNotBeDisplayed();

    @Override
    WebLink shouldHaveBounds(Bounds bounds);

    @Override
    WebLink shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebLink shouldHaveText(StringValue stringValue);

    @Override
    WebLink shouldLooksLike(Screenshot screenshot);

    @Override
    WebLink stateShouldBeDisplayed(String stateName);

}
