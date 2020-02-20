package io.perfeccionista.framework.utils;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.ConstructorNotFoundException;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CONSTRUCTOR_NOT_FOUND;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * TODO: JavaDoc
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new ConstructorNotFoundException(CONSTRUCTOR_NOT_FOUND.getMessage(clazz.getCanonicalName(), parameterTypes));
        }
    }

    /**
     * Возвращает список {@link List<Class>} классов, который представляет собой
     * иерархию их наследования от исходного класса до {@link Object}.<br/>
     * @param targetClass   класс {@link Class} для обработки
     * @param ancestorClass класс {@link Class} до которого будет обработана иерархия.
     * @return              классы иерархии
     */
    public static <T> Deque<Class<? extends T>> getClassesWithInheritance(@NotNull Class<? extends T> targetClass, @NotNull Class<T> ancestorClass) {
        Deque<Class<? extends T>> classesWithInheritance = new ArrayDeque<>();
        Class<? extends T> processedClass = targetClass;
        while (ancestorClass.isAssignableFrom(processedClass)) {
            classesWithInheritance.addFirst(processedClass);
            //noinspection unchecked
            processedClass = (Class<T>) processedClass.getSuperclass();
        }
        return classesWithInheritance;
    }

    /**
     * Проверяет что переданный экземпляр {@param originalInstance} является наследником класса {@param comparedClass}
     * @param originalInstance экземпляр для проверки
     * @param comparedClass    класс, по отношению к которому проводится проверка принадлежности
     * @return является ли экземпляр наследником переданного класса
     */
    public static boolean isSubtypeOf(@NotNull Object originalInstance, @NotNull Class<?> comparedClass) {
        return isSubtypeOf(originalInstance.getClass(), comparedClass);
    }

    /**
     * Проверяет что переданный класс {@param originalClass} является наследником класса {@param comparedClass}
     * @param originalClass класс для проверки
     * @param comparedClass класс, по отношению к которому проводится проверка принадлежности
     * @return является ли класс {@param originalClass} наследником класса {@param comparedClass}
     */
    public static boolean isSubtypeOf(@NotNull Class<?> originalClass, @NotNull Class<?> comparedClass) {
        return comparedClass.isAssignableFrom(originalClass);
    }

}
