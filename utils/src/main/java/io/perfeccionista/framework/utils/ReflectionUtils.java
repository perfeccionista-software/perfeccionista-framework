package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FieldAccessException;
import io.perfeccionista.framework.exceptions.FieldNotFoundException;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.ConstructorNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CONSTRUCTOR_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_READING_ERROR;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_WRITING_ERROR;

// TODO: Отредактировать утилитные классы и джавадоки
public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * Получить экземпляр из объекта по переданному полю
     *
     * @param fieldName     какое поле искать в объекте
     * @param parentElement объект для поиска
     * @return экземпляр, записанный в переданном поле переданного объекта
     */
    @SuppressWarnings("unchecked")
    public static <T> T readField(String fieldName, Object parentElement) {
        try {
            Field targetField = getAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
            targetField.setAccessible(true);
            // TODO Проверить типы
            return (T) targetField.get(parentElement);
        } catch (IllegalAccessException e) {
            throw new FieldAccessException(FIELD_READING_ERROR.getMessage(fieldName), e);
        }
    }

    /**
     * Назначить значение в объекте по переданогму полю
     *
     * @param parentElement объект для поиска
     * @param value         значение, которое следует разменстить в поле
     * @return объект
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T writeField(String fieldName, T parentElement, Object value) {
        try {
            Field targetField = getAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
            targetField.setAccessible(true);
            targetField.set(parentElement, value);
            return parentElement;
        } catch (IllegalAccessException e) {
            throw new FieldAccessException(FIELD_WRITING_ERROR.getMessage(fieldName), e);
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
     * Проверка, что тип переданного поля является наследником от переданного класса.
     *
     * @param fieldToTest поле, тип которого нужно проверить
     * @param clazz       класс, к которому проверяем приводимость - должен быть среди родителей
     * @return является ли поле наследником переданного класса
     */
    public static boolean isSubtypeOf(Field fieldToTest, Class<?> clazz) {
        return isSubtypeOf(fieldToTest.getType(), clazz);
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
     * TODO: JavaDoc
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new ConstructorNotFoundException(CONSTRUCTOR_NOT_FOUND.getMessage(clazz.getCanonicalName(), parameterTypes));
        }
    }

//    public static <T> T setFieldValue(String fieldName, T instance, Object value) {
//        Field field = getAccessibleFieldWithInheritance(instance.getClass(), "field");
//        writeField(field, instance, elementField);
//    }


    /**
     * Получить поле из объекта с учетом полей родителей
     *
     * @param processedClass     тип объекта
     * @param fieldName имя поля
     * @return поле, объявленное доступным
     */
    public static Field getAccessibleFieldWithInheritance(Class<?> processedClass, String fieldName) {
        Field targetField = getFieldsWithInheritance(processedClass)
                .stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new FieldNotFoundException(FIELD_NOT_FOUND.getMessage(processedClass.getCanonicalName(), fieldName)));
        targetField.setAccessible(true);
        return targetField;
    }

    /**
     * Получить поля из объекта с учетом полей родителей
     * // TODO: Сделать сравнение по феншую
     * @param clazz тип объекта
     * @return список всех полей класса с учетом полей в родителях
     */
    public static List<Field> getFieldsWithInheritance(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        } while (!"java.lang.Object".equals(clazz.getName()));
        return fields;
    }

//
//    // TODO: Проверить логику этого метода
//    public static List<Method> getMethodsWithInheritance(Class<?> clazz, ) {
//        List<Method> methods = new ArrayList<>();
//        do {
//            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
//            clazz = clazz.getSuperclass();
//        } while (!"java.lang.Object".equals(clazz.getName()));
//        return methods;
//    }
//
//
    /**
     * Метод возвращает цепочку классов, которые являются предками заданного класса {@code inheritorClass}
     * до ограничивающего класса {@code ancestorClass}.<br/>
     * Например, у нас есть цепочка классов:<br/>
     * {@code Object -> AbstractCustomizableElement -> Button -> MyButton -> MyTunedButton}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.DESC)}<br/>
     * Мы получим результат {MyTunedButton.class, MyButton.class, Button.class}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.ASC)}<br/>
     * Мы получим результат {Button.class, MyButton.class, MyTunedButton.class}<br/>
     *
     * @param ancestorClass   класс-ограничитель цепочки наследования (класс-предок)
     * @param inheritorClass класс который нам необходимо исследовать (класс-потомок)
     * @param order     порядок возвращаемых значений
     * @param <T>       тип класса-ограничителя
     */
    @SuppressWarnings("unchecked")
    public static <T> Deque<Class<T>> getClassInheritors(@NotNull Class<T> ancestorClass,
                                                         @NotNull Class<? extends T> inheritorClass,
                                                         @NotNull Order order) {
        Deque<Class<T>> classChain = new ArrayDeque<>();
        Class<?> processedClass = inheritorClass;
        while (ancestorClass.isAssignableFrom(processedClass)) {
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
     * TODO: Поправить описание
     * Метод возвращает цепочку классов, которые являются предками заданного класса {@code inheritorClass}
     * до ограничивающего класса {@code ancestorClass}.<br/>
     * Например, у нас есть цепочка классов:<br/>
     * {@code Object -> AbstractCustomizableElement -> Button -> MyButton -> MyTunedButton}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.DESC)}<br/>
     * Мы получим результат {MyTunedButton.class, MyButton.class, Button.class}<br/>
     * <br/>
     * При вызове метода {@code getClassChain(Button.class, MyTunedButton.class, Order.ASC)}<br/>
     * Мы получим результат {Button.class, MyButton.class, MyTunedButton.class}<br/>
     *
     * @param ancestorInterface класс-ограничитель цепочки наследования (класс-предок)
     * @param <T>               тип класса-ограничителя
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<Class<? extends T>> getInheritedInterfaces(Class<T> ancestorInterface, Class<? extends T> processedInterface) {
        Set<Class<? extends T>> result = new HashSet<>();
        result.add(processedInterface);
        for (Class<?> foundInterface : processedInterface.getInterfaces()) {
            if (ancestorInterface.isAssignableFrom(foundInterface)) {
                Class<? extends T> typifiedFoundInterface = (Class<? extends T>) foundInterface;
                result.addAll(getInheritedInterfaces(ancestorInterface, typifiedFoundInterface));
            }
        }
        return result;
    }


    /**
     *
     */
    public enum Order {
        ASC, DESC
    }

}
