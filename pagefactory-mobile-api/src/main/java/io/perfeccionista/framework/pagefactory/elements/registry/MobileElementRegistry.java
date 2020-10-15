package io.perfeccionista.framework.pagefactory.elements.registry;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;

public class MobileElementRegistry {

    protected Map<String, MobileBlock> innerBlocks = new HashMap<>();
    protected Map<String, MobileChildElement> innerElements = new HashMap<>();

    protected Map<String, Method> innerMethods = new HashMap<>();

    // Вынести реализацию этих методов в утилитный класс и передавать туда blocks & elements
    protected Optional<MobileChildElement> getElementByName(String elementName) {
        MobileChildElement lookupElement = null;
        if (innerElements.containsKey(elementName)) {
            lookupElement = innerElements.get(elementName);
        } else if (innerBlocks.containsKey(elementName)) {
            lookupElement = innerBlocks.get(elementName);
        } else {
            for (MobileBlock block : innerBlocks.values()) {
                Optional<MobileChildElement> result = block.getElementRegistry().getElementByName(elementName);
                if (result.isPresent()) {
                    lookupElement = result.get();
                    break;
                }
            }
        }
        return Optional.ofNullable(lookupElement);
    }

//    public Optional<MobileChildElement> getElementByPath(String elementPath) {
//        String[] elementNameSequence = splitPathByArrow(elementPath);
//        MobileChildElement lookupElement = null;
//        for (String elementName : elementNameSequence) {
//            Optional<MobileChildElement> intermediateResult;
//            if (null == lookupElement) {
//                intermediateResult = getElementByName(elementName);
//            } else {
//                if (!isSubtypeOf(lookupElement, MobileBlock.class)) {
//                    throw new WebElementSearchException(INCORRECT_PARENT_ELEMENT.getMessage(lookupElement.getClass().getCanonicalName()))
//                            .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
//                }
//                intermediateResult = ((MobileBlock) lookupElement).getElementRegistry().getElementByName(elementName);
//            }
//            if (intermediateResult.isPresent()) {
//                lookupElement = intermediateResult.get();
//            } else {
//                return Optional.empty();
//            }
//        }
//        return Optional.empty();
//    }
//
//    public <T extends MobileChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
//        Optional<MobileChildElement> optionalLookupElement = getElementByPath(elementPath);
//        if (optionalLookupElement.isPresent()) {
//            MobileChildElement lookupElement = optionalLookupElement.get();
//            if (isSubtypeOf(lookupElement, elementClass)) {
//                return Optional.of(elementClass.cast(lookupElement));
//            }
//            throw new WebElementCastException(CANT_CAST_ELEMENT.getMessage(elementClass.getCanonicalName()))
//                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", lookupElement.toString())));
//        }
//        return Optional.empty();
//    }
//
//    public void invokeMethodByName(String methodName, MobileParentElement targetElement, Object... args) {
//        Method method = Optional.ofNullable(innerMethods.get(methodName))
//                .orElseThrow(() -> new MethodNotDeclaredException(METHOD_NOT_DECLARED.getMessage(methodName))
//                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", targetElement.toString()))));
//        ReflectionUtils.invokeMethod(method, targetElement, args);
//    }


}
