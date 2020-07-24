package io.perfeccionista.framework.pagefactory.elements.registry;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementCastException;
import io.perfeccionista.framework.exceptions.ElementSearchException;
import io.perfeccionista.framework.exceptions.MethodNotDeclaredException;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.CANT_CAST_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.INCORRECT_PARENT_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.METHOD_NOT_DECLARED;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;

public class WebElementRegistry implements ElementRegistry {

    protected Map<String, WebChildElement> elementsByMethodName = new HashMap<>();
    protected Map<Method, WebChildElement> elementsByMethod = new HashMap<>();
    protected Map<String, WebChildElement> elementsByName = new HashMap<>();

    // TODO: Это предназначено для именованных методов, но пока не используется
    protected Map<String, Method> innerMethods = new HashMap<>();

    protected WebElementRegistry(List<WebChildElement> webChildElements) {
        webChildElements
                .forEach(webChildElement -> {
                    WebElementIdentifier elementIdentifier = webChildElement.getElementIdentifier();
                    elementsByMethodName.put(elementIdentifier.getElementMethod().getName(), webChildElement);
                    elementsByMethod.put(elementIdentifier.getElementMethod(), webChildElement);
                    elementIdentifier.forEachName(webChildElementName -> elementsByName.put(webChildElementName, webChildElement));
                });
    }

    public static WebElementRegistry of(@NotNull List<WebChildElement> webChildElements) {
        return new WebElementRegistry(webChildElements);
    }

    public Optional<WebChildElement> getElementByMethod(@NotNull Method method) {
        WebChildElement lookupElement = null;
        if (elementsByMethod.containsKey(method)) {
            lookupElement = elementsByMethod.get(method);
        } else {
            for (WebChildElement element : elementsByMethod.values()) {
                if (isSubtypeOf(element, WebParentElement.class)) {
                    Optional<WebChildElement> result = ((WebParentElement) element).getElementRegistry().getElementByMethod(method);
                    if (result.isPresent()) {
                        lookupElement = result.get();
                        break;
                    }
                }
            }
        }
        if (lookupElement != null) {
            lookupElement.getElementIdentifier().setLastUsedName(method.getName());
        }
        return Optional.ofNullable(lookupElement);
    }

    public <T> Optional<T> getElementByMethod(@NotNull Method method, Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByMethod(method);
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


    protected Optional<WebChildElement> getElementByMethodName(String methodName) {
        WebChildElement lookupElement = null;
        if (elementsByMethodName.containsKey(methodName)) {
            lookupElement = elementsByMethodName.get(methodName);
        } else {
            for (WebChildElement element : elementsByMethodName.values()) {
                if (isSubtypeOf(element, WebParentElement.class)) {
                    Optional<WebChildElement> result = ((WebParentElement) element).getElementRegistry().getElementByMethodName(methodName);
                    if (result.isPresent()) {
                        lookupElement = result.get();
                        break;
                    }
                }
            }
        }
        if (lookupElement != null) {
            lookupElement.getElementIdentifier().setLastUsedName(methodName);
        }
        return Optional.ofNullable(lookupElement);
    }

    public <T> Optional<T> getElementByMethodName(@NotNull String methodName, Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByMethodName(methodName);
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


    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    public Optional<WebChildElement> getElementByPath(String elementPath) {
        String[] elementNameSequence = parseElementPath(elementPath);
        WebChildElement lookupElement = null;
        for (String elementName : elementNameSequence) {
            Optional<WebChildElement> intermediateResult;
            if (null == lookupElement) {
                intermediateResult = getElementByName(elementName);
            } else {
                if (!isSubtypeOf(lookupElement, WebParentElement.class)) {
                    throw new ElementSearchException(INCORRECT_PARENT_ELEMENT.getMessage(lookupElement.getClass().getCanonicalName()))
                            .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
                }
                intermediateResult = ((WebParentElement) lookupElement).getElementRegistry().getElementByName(elementName);
            }
            if (intermediateResult.isPresent()) {
                lookupElement = intermediateResult.get();
            } else {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(lookupElement);
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

    protected Optional<WebChildElement> getElementByName(String elementName) {
        WebChildElement lookupElement = null;
        if (elementsByName.containsKey(elementName)) {
            lookupElement = elementsByName.get(elementName);
        } else {
            for (WebChildElement element : elementsByName.values()) {
                if (isSubtypeOf(element, WebParentElement.class)) {
                    Optional<WebChildElement> result = ((WebParentElement) element).getElementRegistry().getElementByName(elementName);
                    if (result.isPresent()) {
                        lookupElement = result.get();
                        break;
                    }
                }
            }
        }
        if (lookupElement != null) {
            lookupElement.getElementIdentifier().setLastUsedName(elementName);
        }
        return Optional.ofNullable(lookupElement);
    }

    public void invokeMethodByName(String methodName, WebParentElement targetElement, Object... args) {
        Method method = Optional.ofNullable(innerMethods.get(methodName))
                .orElseThrow(() -> new MethodNotDeclaredException(METHOD_NOT_DECLARED.getMessage(methodName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", targetElement.toString()))));
        ReflectionUtils.invokeMethod(method, targetElement, args);
    }

}
