package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
public interface WebTextBlock extends WebChildElement,
        GetTextAvailable {

    @Override
    WebTextBlock hoverTo(boolean withOutOfBounds);

    @Override
    WebTextBlock scrollTo();

    @Override
    WebTextBlock shouldBeDisplayed();

    @Override
    WebTextBlock shouldNotBeDisplayed();

    @Override
    WebTextBlock shouldHaveBounds(Bounds bounds);

    @Override
    WebTextBlock shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebTextBlock shouldHaveText(StringValue stringValue);

    @Override
    WebTextBlock shouldLooksLike(Screenshot screenshot);

    @Override
    WebTextBlock stateShouldBeDisplayed(String stateName);

}
