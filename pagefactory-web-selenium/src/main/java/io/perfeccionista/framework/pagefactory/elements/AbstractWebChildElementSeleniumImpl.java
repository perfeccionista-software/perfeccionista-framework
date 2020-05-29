package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetDimensions;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetWebElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsIsComponentDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.HOVER;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebSeleniumMethods.GET_WEB_ELEMENT_METHOD;

// TODO: В конфигурации сделать возможность устанавливать настройки элемента параметром
//  config.put(WebButton.class, new WebButtonImpl().withElementAction(SCROLL_TO_METHOD, JsScrollToWithDelay.class)

@ElementAction(name = GET_WEB_ELEMENT_METHOD, implementation = JsGetWebElement.class)
@ElementAction(name = GET_DIMENSIONS_METHOD, implementation = JsGetDimensions.class)
@ElementAction(name = GET_SCREENSHOT_METHOD, implementation = JsGetScreenshot.class)
@ElementAction(name = GET_PROPERTY_VALUE_METHOD, implementation = JsGetPropertyValue.class)
@ElementAction(name = HOVER_TO_METHOD, implementation = SeleniumHoverTo.class)
@ElementAction(name = IS_DISPLAYED_METHOD, implementation = JsIsDisplayed.class)
@ElementAction(name = IS_COMPONENT_DISPLAYED_METHOD, implementation = JsIsComponentDisplayed.class)
@ElementAction(name = SCROLL_TO_METHOD, implementation = JsScrollTo.class)
// TODO: Сделать базовые заготовки для остальных компонентов с указанием executeOnCall
//@WebLocator(component = HOVER, invokeOnCall = {ScrollToFunctionInvoke.class, IsDisplayedFunctionInvoke.class})
public abstract class AbstractWebChildElementSeleniumImpl extends AbstractChildElement<WebParentElement> implements WebChildElement {

//    protected ElementPropertiesRegistry<WebElementPropertyHolder> elementPropertiesRegistry;
//    protected ElementMethodsRegistry<WebElementMethodImplementation<?>> elementMethodsRegistry;
//
//    @Override
//    public WebDriverInstance getDriverInstance() {
//        return parent.getDriverInstance();
//    }
//
//    @Override
//    public <T> WebElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType) {
//        return elementMethodsRegistry.getElementMethod(methodType, returnType);
//    }
//
//    @Override
//    public LocatorChain getLocatorChainTo(String locatorName) {
//        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
//        if (optionalLocator.isPresent()) {
//            return getLocatorChain().addLocator(optionalLocator.get());
//        }
//        return getLocatorChain();
//    }
//
//    @Override
//    public LocatorChain getLocatorChain() {
//        return getParent().getLocatorChain().addLocator(getLocator(ROOT));
//    }
//
//    public WebElement getWebElement() {
//        return getMethodImplementation(GET_WEB_ELEMENT_METHOD, WebElement.class).execute(this);
//    }
//
//    @Override
//    public Optional<WebElementPropertyHolder> getProperty(String propertyName) {
//        return elementPropertiesRegistry.getElementProperty(propertyName);
//    }
//
//    @Override
//    public String getPropertyValue(String propertyName) {
//        WebElementPropertyHolder elementPropertyHolder = getProperty(propertyName)
//                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName))
//                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
//        return getMethodImplementation(GET_PROPERTY_VALUE_METHOD, String.class).execute(this, elementPropertyHolder);
//    }
//
//    @Override
//    public boolean isStateDisplayed(String stateName) {
//        LocatorHolder elementStateLocator = getState(stateName)
//                .orElseThrow(() -> new ElementStateNotDeclaredException(ELEMENT_STATE_NOT_DECLARED.getMessage(stateName))
//                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
//        return getMethodImplementation(IS_STATE_DISPLAYED_METHOD, Boolean.class).execute(this, elementStateLocator);
//    }
//
//    @Override
//    public boolean isDisplayed() {
//        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this);
//    }
//
//    @Override
//    public void hoverTo(boolean withOutOfBounds) {
//        getMethodImplementation(HOVER_TO_METHOD, Void.class).execute(this, withOutOfBounds);
//    }
//
//    @Override
//    public void scrollTo() {
//        getMethodImplementation(SCROLL_TO_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public Bounds getBounds() {
//        return getMethodImplementation(GET_BOUNDS_METHOD, Bounds.class).execute(this);
//    }
//
//    @Override
//    public Screenshot getScreenshot() {
//        return getMethodImplementation(GET_SCREENSHOT_METHOD, Screenshot.class).execute(this);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        /**
//         * TODO: Добавлять цепочку полей и классов в чейн билдер или проходить рекурсивно тут?
//         *  Нужен путь к пейджобжекту в виде:
//         *      PageClass:
//         *          FieldClass: fieldName
//         *              ElementClass: elementFieldName
//         */
//
//        sb.append("\nNames:");
//        this.namesRegistry.forEach(name ->
//                sb.append("\n    ").append(name));
//        sb.append("\nRequired: ").append(required);
//        sb.append("\nClass: ").append(this.getClass().getCanonicalName());
//        sb.append("\nLocatorChain:");
//        sb.append("\n    ").append(getLocatorChain().getLocatorChain().stream()
//                .map(LocatorHolder::getShortDescription)
//                .collect(Collectors.joining(" -> ")));
//        sb.append("\nLocators:");
//        this.locatorRegistry.forEach((key, value) ->
//                sb.append("\n    Locator: ").append(key).append(" = ").append(value.toString()));
//        sb.append("\nElementMethods:");
//        this.elementMethodsRegistry.forEach((key, value) ->
//                sb.append("\n    ElementMethod: ").append(key).append(" = ").append(value.getClass().getCanonicalName()));
//        sb.append("\nElementProperties:");
//        this.elementPropertiesRegistry.forEach((key, value) ->
//                sb.append("\n    ElementProperty: ").append(key).append(" = ").append(value.toString()));
//        return sb.toString();
//    }

}
