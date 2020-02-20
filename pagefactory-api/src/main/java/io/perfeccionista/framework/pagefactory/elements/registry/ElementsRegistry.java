package io.perfeccionista.framework.pagefactory.elements.registry;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementCastException;
import io.perfeccionista.framework.exceptions.ElementSearchException;
import io.perfeccionista.framework.exceptions.EmptyElementPathException;
import io.perfeccionista.framework.exceptions.MethodNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.commons.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.CANT_CAST_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.EMPTY_ELEMENT_PATH;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.INCORRECT_PARENT_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.METHOD_NOT_DECLARED;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;

public class ElementsRegistry {

    protected Map<String, Block> innerBlocks = new HashMap<>();
    protected Map<String, Element> innerElements = new HashMap<>();

    protected Map<String, Method> innerMethods = new HashMap<>();

    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    protected Optional<Element> getElementByName(String elementName) {
        Element lookupElement = null;
        if (innerElements.containsKey(elementName)) {
            lookupElement = innerElements.get(elementName);
        } else if (innerBlocks.containsKey(elementName)) {
            lookupElement = innerBlocks.get(elementName);
        } else {
            for (Block block : innerBlocks.values()) {
                Optional<Element> result = block.getElementRegistry().getElementByName(elementName);
                if (result.isPresent()) {
                    lookupElement = result.get();
                    break;
                }
            }
        }
        return Optional.ofNullable(lookupElement);
    }

    public Optional<Element> getElementByPath(String elementPath) {
        String[] elementNameSequence = parseElementPath(elementPath);
        Element lookupElement = null;
        for (String elementName : elementNameSequence) {
            Optional<Element> intermediateResult;
            if (null == lookupElement) {
                intermediateResult = getElementByName(elementName);
            } else {
                if (!isSubtypeOf(lookupElement, Block.class)) {
                    throw new ElementSearchException(INCORRECT_PARENT_ELEMENT.getMessage(lookupElement.getClass().getCanonicalName()))
                            .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
                }
                intermediateResult = ((Block) lookupElement).getElementRegistry().getElementByName(elementName);
            }
            if (intermediateResult.isPresent()) {
                lookupElement = intermediateResult.get();
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public <T extends Element> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        Optional<Element> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            Element lookupElement = optionalLookupElement.get();
            if (isSubtypeOf(lookupElement, elementClass)) {
                return Optional.of(elementClass.cast(lookupElement));
            }
            throw new ElementCastException(CANT_CAST_ELEMENT.getMessage(elementClass.getCanonicalName()))
                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
        }
        return Optional.empty();
    }

    public void invokeMethodByName(String methodName, ParentElement targetElement, Object... args) {
        Method method = Optional.ofNullable(innerMethods.get(methodName))
                .orElseThrow(() -> new MethodNotDeclaredException(METHOD_NOT_DECLARED.getMessage(methodName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", targetElement.toString()))));
        ReflectionUtils.invokeMethod(method, targetElement, args);
    }

    protected String[] parseElementPath(String elementPath) {
        String trimmedElementPath = elementPath.trim();
        if (StringUtils.isBlank(trimmedElementPath)) {
            throw new EmptyElementPathException(EMPTY_ELEMENT_PATH.getMessage());
        }
        return Arrays.stream(trimmedElementPath.split("\\s->\\s")).map(String::trim).toArray(String[]::new);
    }

}
