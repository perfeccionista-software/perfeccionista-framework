package io.perfeccionista.framework.utils;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.ConstructorNotFoundException;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;

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

    /**
     * Метод возвращает цепочку классов, которые являются предками заданного класса {@code inheritorClass}
     * до ограничивающего класса {@code baseClass}.<br/>
     * Например, у нас есть цепочка классов:<br/>
     * {@code Object -> AbstractCustomizableElement -> Button -> MyButton -> MyTunedButton}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.DESC)}<br/>
     * Мы получим результат {MyTunedButton.class, MyButton.class, Button.class}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.ASC)}<br/>
     * Мы получим результат {Button.class, MyButton.class, MyTunedButton.class}<br/>
     *
     * @param baseClass   класс-ограничитель цепочки наследования (класс-предок)
     * @param inheritorClass класс который нам необходимо исследовать (класс-потомок)
     * @param order     порядок возвращаемых значений
     * @param <T>       тип класса-ограничителя
     */
    @SuppressWarnings("unchecked")
    public static <T> Deque<Class<T>> getClassInheritors(@NotNull Class<T> baseClass,
                                                         @NotNull Class<? extends T> inheritorClass,
                                                         @NotNull Order order) {
        Deque<Class<T>> classChain = new ArrayDeque<>();
        Class<?> processedClass = inheritorClass;
        while (baseClass.isAssignableFrom(processedClass)) {
            if (order == Order.ASC) {
                classChain.addFirst((Class<T>) processedClass);
            }
            if (order == Order.DESC) {
                classChain.addLast((Class<T>) processedClass);
            }
            processedClass = processedClass.getSuperclass();
        }
        return classChain;
    }

    /**
     *
     */
    public enum Order {
        ASC, DESC
    }

}
