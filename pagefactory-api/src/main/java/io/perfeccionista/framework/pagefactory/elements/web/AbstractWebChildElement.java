package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementPropertyNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementStateNotDeclaredException;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodsRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetBounds;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsIsStateDisplayed;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetWebElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.web.methods.WebElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_STATE_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_WEB_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_STATE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_METHOD;

@ElementMethod(type = GET_WEB_ELEMENT_METHOD, implementation = JsGetWebElement.class)
@ElementMethod(type = GET_BOUNDS_METHOD, implementation = JsGetBounds.class)
@ElementMethod(type = GET_SCREENSHOT_METHOD, implementation = JsGetScreenshot.class)
@ElementMethod(type = GET_PROPERTY_VALUE_METHOD, implementation = JsGetPropertyValue.class)
@ElementMethod(type = HOVER_TO_METHOD, implementation = SeleniumHoverTo.class)
@ElementMethod(type = IS_DISPLAYED_METHOD, implementation = JsIsDisplayed.class)
@ElementMethod(type = IS_STATE_DISPLAYED_METHOD, implementation = JsIsStateDisplayed.class)
@ElementMethod(type = SCROLL_TO_METHOD, implementation = JsScrollTo.class)
public abstract class AbstractWebChildElement extends AbstractChildElement<WebParentElement> implements WebChildElement {

    protected ElementMethodsRegistry<WebElementMethodImplementation<?>> elementMethodsRegistry;

    @Override
    public DriverInstance<RemoteWebDriver> getDriverInstance() {
        return parent.getDriverInstance();
    }

    @Override
    public <T> WebElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType) {
        return elementMethodsRegistry.getElementMethod(methodType, returnType);
    }

    @Override
    public LocatorChain getLocatorChainTo(String locatorName) {
        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public LocatorChain getLocatorChain() {
        return getParent().getLocatorChain().addLocator(getLocator(ROOT));
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
    public OperationResult<Void> hoverTo(boolean withOutOfBounds) {
        return getMethodImplementation(HOVER_TO_METHOD, Void.class).execute(this, withOutOfBounds);
    }

    @Override
    public OperationResult<Void> scrollTo() {
        return getMethodImplementation(SCROLL_TO_METHOD, Void.class).execute(this);
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
        StringBuilder sb = new StringBuilder();

        /**
         * TODO: Добавлять цепочку полей и классов в чейн билдер или проходить рекурсивно тут?
         *  Нужен путь к пейджобжекту в виде:
         *      PageClass:
         *          FieldClass: fieldName
         *              ElementClass: elementFieldName
         */

        sb.append("\nNames:");
        this.namesRegistry.forEach(name ->
                sb.append("\n    ").append(name));
        sb.append("\nRequired: ").append(required);
        sb.append("\nClass: ").append(this.getClass().getCanonicalName());
        sb.append("\nLocatorChain:");
        sb.append("\n    ").append(getLocatorChain().getLocatorChain().stream()
                .map(LocatorHolder::getShortDescription)
                .collect(Collectors.joining(" -> ")));
        sb.append("\nLocators:");
        this.locatorRegistry.forEach((key, value) ->
                sb.append("\n    Locator: ").append(key).append(" = ").append(value.toString()));
        sb.append("\nElementMethods:");
        this.elementMethodsRegistry.forEach((key, value) ->
                sb.append("\n    ElementMethod: ").append(key).append(" = ").append(value.getClass().getCanonicalName()));
        sb.append("\nElementProperties:");
        this.elementPropertiesRegistry.forEach((key, value) ->
                sb.append("\n    ElementProperty: ").append(key).append(" = ").append(value.toString()));
        sb.append("\nElementStates:");
        this.elementStatesRegistry.forEach((key, value) ->
                sb.append("\n    ElementState: ").append(key).append(" = ").append(value.toString()));
        return sb.toString();
    }

}
