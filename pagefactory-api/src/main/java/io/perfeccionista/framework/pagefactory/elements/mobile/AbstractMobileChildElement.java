package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementPropertyNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementStateNotDeclaredException;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodsRegistry;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumGetBounds;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumGetWebElement;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumIsStateDisplayed;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.AppiumSwipeTo;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.MobileElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_STATE_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_WEB_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_STATE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_METHOD;

@ElementMethod(type = GET_WEB_ELEMENT_METHOD, implementation = AppiumGetWebElement.class)
@ElementMethod(type = GET_BOUNDS_METHOD, implementation = AppiumGetBounds.class)
@ElementMethod(type = GET_SCREENSHOT_METHOD, implementation = AppiumGetScreenshot.class)
@ElementMethod(type = GET_PROPERTY_VALUE_METHOD, implementation = AppiumGetPropertyValue.class)
@ElementMethod(type = IS_DISPLAYED_METHOD, implementation = AppiumIsDisplayed.class)
@ElementMethod(type = IS_STATE_DISPLAYED_METHOD, implementation = AppiumIsStateDisplayed.class)
@ElementMethod(type = SWIPE_TO_METHOD, implementation = AppiumSwipeTo.class)
public abstract class AbstractMobileChildElement extends AbstractChildElement<MobileParentElement> implements MobileChildElement {

    protected ElementMethodsRegistry<MobileElementMethodImplementation<?>> elementMethodsRegistry;

    @Override
    public DriverInstance<AppiumDriver<?>> getDriverInstance() {
        return parent.getDriverInstance();
    }

    @Override
    public <T> MobileElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType) {
        return elementMethodsRegistry.getElementMethod(methodType, returnType);
    }

    public OperationResult<WebElement> getWebElement() {
        return getMethodImplementation(GET_WEB_ELEMENT_METHOD, WebElement.class).execute(this);
    }

    @Override
    public OperationResult<String> getPropertyValue(String propertyName) {
        ElementPropertyHolder elementPropertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
        return getMethodImplementation(GET_PROPERTY_VALUE_METHOD, String.class).execute(this, elementPropertyHolder);
    }

    @Override
    public OperationResult<Boolean> isStateDisplayed(String stateName) {
        ElementStateHolder elementStateHolder = getState(stateName)
                .orElseThrow(() -> new ElementStateNotDeclaredException(ELEMENT_STATE_NOT_DECLARED.getMessage(stateName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
        return getMethodImplementation(IS_STATE_DISPLAYED_METHOD, Boolean.class).execute(this, elementStateHolder);
    }

    @Override
    public OperationResult<Boolean> isDisplayed() {
        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this);
    }

    @Override
    public OperationResult<Void> swipeTo() {
        return getMethodImplementation(SWIPE_TO_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<Bounds> getBounds() {
        return getMethodImplementation(GET_BOUNDS_METHOD, Bounds.class).execute(this);
    }

    @Override
    public OperationResult<Screenshot> getScreenshot() {
        return getMethodImplementation(GET_SCREENSHOT_METHOD, Screenshot.class).execute(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nElementMethods:");
        this.elementMethodsRegistry.forEach((key, value) ->
                sb.append("\n    ElementMethod: ").append(key).append(" = ").append(value.getClass().getCanonicalName()));
        sb.append("\n");
        return sb.toString();
    }

}
