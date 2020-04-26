package io.perfeccionista.framework.elements;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.browser.MobileDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.ElementActionsRegistry;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertiesRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

import java.util.Optional;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.pagefactory.elements.components.MobileComponents.ROOT;

//@ElementAction(name = GET_WEB_ELEMENT_METHOD, implementation = AppiumGetMobileElement.class)
//@ElementAction(name = GET_BOUNDS_METHOD, implementation = AppiumGetBounds.class)
//@ElementAction(name = GET_SCREENSHOT_METHOD, implementation = AppiumGetScreenshot.class)
//@ElementAction(name = GET_PROPERTY_VALUE_METHOD, implementation = AppiumGetPropertyValue.class)
//@ElementAction(name = IS_DISPLAYED_METHOD, implementation = AppiumIsDisplayed.class)
//@ElementAction(name = IS_COMPONENT_DISPLAYED_METHOD, implementation = AppiumIsComponentDisplayed.class)
//@ElementAction(name = SWIPE_TO_METHOD, implementation = AppiumSwipeTo.class)
public abstract class AbstractMobileChildElement extends AbstractChildElement<MobileParentElement> implements MobileChildElement {

    protected ElementPropertiesRegistry<MobileElementPropertyHolder> elementPropertiesRegistry;
    protected ElementActionsRegistry<MobileElementActionImplementation<?>> elementMethodsRegistry;

    @Override
    public MobileDriverInstance getDriverInstance() {
        return parent.getDriverInstance();
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

//    @Override
//    public MultipleResult<MobileElement> findElements(LocatorChain locatorChain) {
//        // TODO: Implement
//        return null;
//    }

//    @Override
//    public SingleResult<MobileElement> findElement(LocatorChain locatorChain) {
//        // TODO: Implement
//        return null;
//    }

    @Override
    public <T> MobileElementActionImplementation<T> getMethodImplementation(String methodType, Class<T> returnType) {
        return elementMethodsRegistry.getAction(methodType, returnType);
    }

//    public OperationResult<WebElement> getWebElement() {
//        return getMethodImplementation(GET_WEB_ELEMENT_METHOD, WebElement.class).execute(this);
//    }

    @Override
    public Optional<MobileElementPropertyHolder> getProperty(String propertyName) {
        return elementPropertiesRegistry.getElementProperty(propertyName);
    }

//    @Override
//    public OperationResult<String> getPropertyValue(String propertyName) {
//        MobileElementPropertyHolder elementPropertyHolder = getProperty(propertyName)
//                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName))
//                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
//        return getMethodImplementation(GET_PROPERTY_VALUE_METHOD, String.class).execute(this, elementPropertyHolder);
//    }
//
//    @Override
//    public OperationResult<Boolean> isStateDisplayed(String stateName) {
//        LocatorHolder elementStateLocator = getState(stateName)
//                .orElseThrow(() -> new ElementStateNotDeclaredException(ELEMENT_STATE_NOT_DECLARED.getMessage(stateName))
//                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", toString()))));
//        return getMethodImplementation(IS_STATE_DISPLAYED_METHOD, Boolean.class).execute(this, elementStateLocator);
//    }
//
//    @Override
//    public OperationResult<Boolean> isDisplayed() {
//        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> swipeTo() {
//        return getMethodImplementation(SWIPE_TO_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Bounds> getBounds() {
//        return getMethodImplementation(GET_BOUNDS_METHOD, Bounds.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Screenshot> getScreenshot() {
//        return getMethodImplementation(GET_SCREENSHOT_METHOD, Screenshot.class).execute(this);
//    }

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
        return sb.toString();
    }

}
