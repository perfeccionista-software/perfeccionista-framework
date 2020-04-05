package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumIsSelected;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_SELECTED_METHOD;

@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementMethod(type = IS_SELECTED_METHOD, implementation = SeleniumIsSelected.class)
public interface WebRadioButton extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    @Override
    WebRadioButton shouldHaveLabel(StringValue stringValue);

    @Override
    WebRadioButton shouldHaveBounds(Bounds bounds);

    @Override
    WebRadioButton shouldLooksLike(Screenshot screenshot);

    @Override
    WebRadioButton shouldBeDisplayed();

    @Override
    WebRadioButton shouldNotBeDisplayed();

    @Override
    WebRadioButton shouldBeEnabled();

    @Override
    WebRadioButton shouldBeDisabled();

    @Override
    WebRadioButton shouldBeSelected();

    @Override
    WebRadioButton shouldNotBeSelected();

    @Override
    WebRadioButton shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebRadioButton stateShouldBeDisplayed(String stateName);

}
