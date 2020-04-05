package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
public interface WebImage extends WebChildElement,
        ClickAvailable {

    @Override
    WebImage click();

    @Override
    WebImage hoverTo(boolean withOutOfBounds);

    @Override
    WebImage scrollTo();

    @Override
    WebImage shouldBeDisplayed();

    @Override
    WebImage shouldNotBeDisplayed();

    @Override
    WebImage shouldHaveBounds(Bounds bounds);

    @Override
    WebImage shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebImage shouldLooksLike(Screenshot screenshot);

    @Override
    WebImage stateShouldBeDisplayed(String stateName);

}
