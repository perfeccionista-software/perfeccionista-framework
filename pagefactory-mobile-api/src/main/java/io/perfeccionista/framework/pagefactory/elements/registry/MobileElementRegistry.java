package io.perfeccionista.framework.pagefactory.elements.registry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementNotFound;
import io.perfeccionista.framework.exceptions.ElementPathNotResolved;
import io.perfeccionista.framework.exceptions.MethodNotFound;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_PATH_NOT_RESOLVED;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.METHOD_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.MobileElementUtils.castMobileChildElement;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.StringUtils.splitPathByArrow;

public class MobileElementRegistry implements JsonSerializable {

    protected Map<String, MobileChildElement> elementsByMethodName = new HashMap<>();
    protected Map<Method, MobileChildElement> elementsByMethod = new HashMap<>();
    protected Map<String, MobileChildElement> elementsByName = new HashMap<>();

    // TODO: Это предназначено для именованных методов, но пока не используется
    protected Map<String, Method> innerMethods = new HashMap<>();

    protected MobileElementRegistry(List<MobileChildElement> mobileChildElements) {
        mobileChildElements
                .forEach(mobileChildElement -> mobileChildElement.getElementIdentifier()
                        .addElementsByMethodName(elementsByMethodName, mobileChildElement)
                        .addElementsByMethod(elementsByMethod, mobileChildElement)
                        .addElementsByName(elementsByName, mobileChildElement));
    }

    public static MobileElementRegistry of(@NotNull List<MobileChildElement> mobileChildElements) {
        return new MobileElementRegistry(mobileChildElements);
    }

    public static MobileElementRegistry of (@NotNull Map<String, MobileBlock> mobileTableCells) {
        MobileElementRegistry mobileElementRegistry = new MobileElementRegistry(new ArrayList<>());
        mobileTableCells.forEach((mobileTableColumnName, mobileTableColumnCell) ->
                mobileElementRegistry.elementsByName.put(mobileTableColumnName, mobileTableColumnCell));
        return mobileElementRegistry;
    }

    public Optional<MobileChildElement> getElementByMethod(@NotNull Method method) {
        MobileChildElement lookupElement = null;
        if (elementsByMethod.containsKey(method)) {
            lookupElement = elementsByMethod.get(method);
        } else {
            for (MobileChildElement element : elementsByMethod.values()) {
                if (isSubtypeOf(element, MobileParentElement.class)) {
                    Optional<MobileChildElement> result = ((MobileParentElement) element).getElementRegistry().getElementByMethod(method);
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

    public <T extends MobileChildElement> Optional<T> getElementByMethod(@NotNull Method method, @NotNull Class<T> elementClass) {
        Optional<MobileChildElement> optionalLookupElement = getElementByMethod(method);
        if (optionalLookupElement.isPresent()) {
            MobileChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castMobileChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    public MobileChildElement getRequiredElementByMethod(@NotNull Method method) {
        Optional<MobileChildElement> optionalLookupElement = getElementByMethod(method);
        if (optionalLookupElement.isPresent()) {
            return optionalLookupElement.get();
        }
        throw ElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(method.getName()));
    }

    public <T extends MobileChildElement> T getRequiredElementByMethod(@NotNull Method method, @NotNull Class<T> elementClass) {
        Optional<T> optionalLookupElement = getElementByMethod(method, elementClass);
        if (optionalLookupElement.isPresent()) {
            return optionalLookupElement.get();
        }
        throw ElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(method.getName()));
    }

    protected Optional<MobileChildElement> getElementByMethodName(String methodName) {
        MobileChildElement lookupElement = null;
        if (elementsByMethodName.containsKey(methodName)) {
            lookupElement = elementsByMethodName.get(methodName);
        } else {
            for (MobileChildElement element : elementsByMethodName.values()) {
                if (isSubtypeOf(element, MobileParentElement.class)) {
                    Optional<MobileChildElement> result = ((MobileParentElement) element).getElementRegistry().getElementByMethodName(methodName);
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

    public <T extends MobileChildElement> Optional<T> getElementByMethodName(@NotNull String methodName, @NotNull Class<T> elementClass) {
        Optional<MobileChildElement> optionalLookupElement = getElementByMethodName(methodName);
        if (optionalLookupElement.isPresent()) {
            MobileChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castMobileChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    public Optional<MobileChildElement> getElementByPath(@NotNull String elementPath) {
        String[] elementNameSequence = splitPathByArrow(elementPath);
        MobileChildElement lookupElement = null;
        for (String elementName : elementNameSequence) {
            Optional<MobileChildElement> intermediateResult;
            if (null == lookupElement) {
                intermediateResult = getElementByName(elementName);
            } else {
                if (!isSubtypeOf(lookupElement, MobileParentElement.class)) {
                    throw ElementPathNotResolved.exception(ELEMENT_PATH_NOT_RESOLVED.getMessage(elementPath, lookupElement.getClass().getSimpleName()))
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(lookupElement));
                }
                intermediateResult = ((MobileParentElement) lookupElement).getElementRegistry().getElementByName(elementName);
            }
            if (intermediateResult.isPresent()) {
                lookupElement = intermediateResult.get();
            } else {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(lookupElement);
    }

    public <T extends MobileChildElement> Optional<T> getElementByPath(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        Optional<MobileChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            MobileChildElement lookupElement = optionalLookupElement.get();
            return Optional.of(castMobileChildElement(lookupElement, elementClass));
        }
        return Optional.empty();
    }

    public MobileChildElement getRequiredElementByPath(@NotNull String elementPath) {
        Optional<MobileChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            return optionalLookupElement.get();
        }
        throw ElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(elementPath));
    }

    public <T extends MobileChildElement> T getRequiredElementByPath(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        Optional<MobileChildElement> optionalLookupElement = getElementByPath(elementPath);
        if (optionalLookupElement.isPresent()) {
            MobileChildElement lookupElement = optionalLookupElement.get();
            return castMobileChildElement(lookupElement, elementClass);
        }
        throw ElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(elementPath));
    }








    protected Optional<MobileChildElement> getElementByName(String elementName) {
        MobileChildElement lookupElement = null;
        if (elementsByName.containsKey(elementName)) {
            lookupElement = elementsByName.get(elementName);
        } else {
            for (MobileChildElement element : elementsByName.values()) {
                if (isSubtypeOf(element, MobileParentElement.class)) {
                    Optional<MobileChildElement> result = ((MobileParentElement) element).getElementRegistry().getElementByName(elementName);
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






    public void invokeMethodByName(String methodName, MobileParentElement targetElement, Object... args) {
        Method method = Optional.ofNullable(innerMethods.get(methodName))
                .orElseThrow(() -> MethodNotFound.exception(METHOD_NOT_FOUND.getMessage(methodName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(targetElement)));
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
