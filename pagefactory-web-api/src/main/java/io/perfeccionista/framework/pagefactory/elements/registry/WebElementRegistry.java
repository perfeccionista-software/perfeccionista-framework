package io.perfeccionista.framework.pagefactory.elements.registry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.WebElementNotFound;
import io.perfeccionista.framework.exceptions.WebElementSearch;
import io.perfeccionista.framework.exceptions.MethodNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.INCORRECT_PARENT_ELEMENT;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.METHOD_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.StringUtils.splitPathByArrow;
import static io.perfeccionista.framework.utils.WebElementUtils.castWebChildElement;

public class WebElementRegistry implements JsonSerializable {

    protected Map<String, WebChildElement> elementsByMethodName = new HashMap<>();
    protected Map<Method, WebChildElement> elementsByMethod = new HashMap<>();
    protected Map<String, WebChildElement> elementsByName = new HashMap<>();

    // TODO: Это предназначено для именованных методов, но пока не используется
    protected Map<String, Method> innerMethods = new HashMap<>();

    protected WebElementRegistry(List<WebChildElement> webChildElements) {
        webChildElements
                .forEach(webChildElement -> webChildElement.getElementIdentifier()
                        .addElementsByMethodName(elementsByMethodName, webChildElement)
                        .addElementsByMethod(elementsByMethod, webChildElement)
                        .addElementsByName(elementsByName, webChildElement));
    }

    public static WebElementRegistry of(@NotNull List<WebChildElement> webChildElements) {
        return new WebElementRegistry(webChildElements);
    }

    public static WebElementRegistry of (@NotNull Map<String, WebBlock> webTableCells) {
        WebElementRegistry webElementRegistry = new WebElementRegistry(new ArrayList<>());
        webTableCells.forEach((webTableColumnName, webTableColumnCell) ->
                webElementRegistry.elementsByName.put(webTableColumnName, webTableColumnCell));
        return webElementRegistry;
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

    public <T extends WebChildElement> Optional<T> getElementByMethod(@NotNull Method method, @NotNull Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByMethod(method);
        if (optionalLookupElement.isPresent()) {
            WebChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castWebChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    public WebChildElement getRequiredElementByMethod(@NotNull Method method) {
        Optional<WebChildElement> optionalLookupElement = getElementByMethod(method);
        if (optionalLookupElement.isPresent()) {
            return optionalLookupElement.get();
        }
        throw WebElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(method.getName()));
    }

    public <T extends WebChildElement> T getRequiredElementByMethod(@NotNull Method method, @NotNull Class<T> elementClass) {
        Optional<T> optionalLookupElement = getElementByMethod(method, elementClass);
        if (optionalLookupElement.isPresent()) {
            return optionalLookupElement.get();
        }
        throw WebElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(method.getName()));
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

    public <T extends WebChildElement> Optional<T> getElementByMethodName(@NotNull String methodName, @NotNull Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByMethodName(methodName);
        if (optionalLookupElement.isPresent()) {
            WebChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castWebChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    public Optional<WebChildElement> getElementByPath(@NotNull String elementPath) {
        String[] elementNameSequence = splitPathByArrow(elementPath);
        WebChildElement lookupElement = null;
        for (String elementName : elementNameSequence) {
            Optional<WebChildElement> intermediateResult;
            if (null == lookupElement) {
                intermediateResult = getElementByName(elementName);
            } else {
                if (!isSubtypeOf(lookupElement, WebParentElement.class)) {
                    throw WebElementSearch.exception(INCORRECT_PARENT_ELEMENT.getMessage(lookupElement.getClass().getCanonicalName()))
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(lookupElement));
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

    public <T extends WebChildElement> Optional<T> getElementByPath(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            WebChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castWebChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    public <T extends WebChildElement> T getRequiredElementByPath(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        Optional<WebChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            WebChildElement lookupElement = optionalLookupElement.get();
            return castWebChildElement(lookupElement, elementClass);
        }
        throw WebElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(elementPath));
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
                .orElseThrow(() -> MethodNotFound.exception(METHOD_NOT_FOUND.getMessage(methodName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(targetElement)));
        ReflectionUtils.invokeMethod(method, targetElement, args);
    }






    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ArrayNode methodNamesNode = rootNode.putArray("methodNames");
        elementsByMethodName.keySet().forEach(methodNamesNode::add);
        ArrayNode namesNode = rootNode.putArray("names");
        elementsByName.keySet().forEach(namesNode::add);
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
