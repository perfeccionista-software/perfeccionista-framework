package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.ElementNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.WebChildElementFrame;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_FOUND;
import static io.perfeccionista.framework.measurements.Order.DESC;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findInheritedInterfaces;
import static io.perfeccionista.framework.utils.ReflectionUtilsForMethods.findMethods;
import static java.lang.reflect.Modifier.isStatic;

public class WebElementUtils {

    private WebElementUtils() {
    }

    public static @NotNull <T extends WebChildElement> T castWebChildElement(@NotNull WebChildElement elementToCast, @NotNull Class<T> castType) {
        if (isSubtypeOf(elementToCast, castType)) {
            return castType.cast(elementToCast);
        }
        throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(castType.getCanonicalName()))
                .addFirstAttachmentEntry(WebElementAttachmentEntry.of(elementToCast));
    }

    public static @NotNull WebChildElementFrame castWebChildElementFrame(@NotNull WebChildElement elementToCast) {
        if (isSubtypeOf(elementToCast, WebChildElementFrame.class)) {
            return WebChildElementFrame.class.cast(elementToCast);
        }
        throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(WebChildElementFrame.class.getCanonicalName()))
                .addFirstAttachmentEntry(WebElementAttachmentEntry.of(elementToCast));
    }

    public static Method getRequiredMethodByName(@NotNull String methodName,
                                                 @NotNull Class<?> methodReturnType,
                                                 @NotNull Class<? extends WebBlock> mappedBlockClass) {
        return getWebChildElementMethods(mappedBlockClass).stream()
                .filter(method -> methodName.equals(method.getName()))
                .filter(method -> methodReturnType.isAssignableFrom(method.getReturnType()))
                .findFirst()
                .orElseThrow(() -> ElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(methodName)));
    }

    public static List<Method> getWebChildElementMethods(@NotNull Class<? extends WebParentElement> processedClass) {
        Predicate<Method> methodPredicate = method -> WebChildElement.class.isAssignableFrom(method.getReturnType())
                && !method.isDefault()
                && !isStatic(method.getModifiers())
                && !WebBlock.class.equals(method.getDeclaringClass())
                && !WebChildElement.class.equals(method.getDeclaringClass());
                // TODO: Проверить что методы не реализованы в имплементации
                // TODO: Проверить что не override или разобраться как различать методы билдера,
                //  которые реализованы в имплементации и методы, возвращающие элемент, который нужно инициализировать
//                && W ebBlock.class != method.getDeclaringClass();
        Set<Method> methods = new HashSet<>();
        methods.addAll(findMethods(processedClass, methodPredicate));
        // Если наш блок наследуется от другого блока (или блоков), то нам нужно получить и их методы тоже
        Deque<Class<? extends WebParentElement>> inheritedParentInterfaces = findInheritedInterfaces(WebParentElement.class, processedClass, DESC);
        for (Class<? extends WebParentElement> inheritedParentInterface : inheritedParentInterfaces) {
            methods.addAll(findMethods(inheritedParentInterface, methodPredicate));
        }
        return new ArrayList<>(methods);
    }

}
