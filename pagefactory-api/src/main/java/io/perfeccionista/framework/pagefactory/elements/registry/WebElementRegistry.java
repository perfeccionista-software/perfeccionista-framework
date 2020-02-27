package io.perfeccionista.framework.pagefactory.elements.registry;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementCastException;
import io.perfeccionista.framework.exceptions.ElementSearchException;
import io.perfeccionista.framework.exceptions.MethodNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebParentElement;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.CANT_CAST_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.INCORRECT_PARENT_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.METHOD_NOT_DECLARED;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;

public class WebElementRegistry implements ElementRegistry {

    protected Map<String, WebBlock> innerBlocks = new HashMap<>();
    protected Map<String, WebChildElement> innerElements = new HashMap<>();

    protected Map<String, Method> innerMethods = new HashMap<>();

    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    protected Optional<WebChildElement> getElementByName(String elementName) {
        WebChildElement lookupElement = null;
        if (innerElements.containsKey(elementName)) {
            lookupElement = innerElements.get(elementName);
        } else if (innerBlocks.containsKey(elementName)) {
            lookupElement = innerBlocks.get(elementName);
        } else {
            for (WebBlock block : innerBlocks.values()) {
                Optional<WebChildElement> result = block.getWebElementRegistry().getElementByName(elementName);
                if (result.isPresent()) {
                    lookupElement = result.get();
                    break;
                }
            }
        }
        return Optional.ofNullable(lookupElement);
    }

    public Optional<WebChildElement> getElementByPath(String elementPath) {
        String[] elementNameSequence = parseElementPath(elementPath);
        WebChildElement lookupElement = null;
        for (String elementName : elementNameSequence) {
            Optional<WebChildElement> intermediateResult;
            if (null == lookupElement) {
                intermediateResult = getElementByName(elementName);
            } else {
                if (!isSubtypeOf(lookupElement, WebBlock.class)) {
                    throw new ElementSearchException(INCORRECT_PARENT_ELEMENT.getMessage(lookupElement.getClass().getCanonicalName()))
                            .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
                }
                intermediateResult = ((WebBlock) lookupElement).getWebElementRegistry().getElementByName(elementName);
            }
            if (intermediateResult.isPresent()) {
                lookupElement = intermediateResult.get();
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public <T extends WebChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            WebChildElement lookupElement = optionalLookupElement.get();
            if (isSubtypeOf(lookupElement, elementClass)) {
                return Optional.of(elementClass.cast(lookupElement));
            }
            throw new ElementCastException(CANT_CAST_ELEMENT.getMessage(elementClass.getCanonicalName()))
                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
        }
        return Optional.empty();
    }

    public void invokeMethodByName(String methodName, WebParentElement targetElement, Object... args) {
        Method method = Optional.ofNullable(innerMethods.get(methodName))
                .orElseThrow(() -> new MethodNotDeclaredException(METHOD_NOT_DECLARED.getMessage(methodName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", targetElement.toString()))));
        ReflectionUtils.invokeMethod(method, targetElement, args);
    }

}
