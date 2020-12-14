package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.ClassCast;
import io.perfeccionista.framework.exceptions.FieldAccess;
import io.perfeccionista.framework.exceptions.FieldNotFound;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.ConstructorNotFound;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
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
    public static <T> T readField(@NotNull String fieldName, @NotNull Object parentElement) {
        try {
            Field targetField = getAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
            // TODO Проверить типы
            return (T) targetField.get(parentElement);
        } catch (IllegalAccessException e) {
            throw FieldAccess.exception(FIELD_READING_ERROR.getMessage(fieldName), e);
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
    public static <T extends Object> T writeField(@NotNull String fieldName, @NotNull T parentElement, @Nullable Object value) {
        try {
            Field targetField = getAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
            targetField.set(parentElement, value);
            return parentElement;
        } catch (IllegalAccessException e) {
            throw FieldAccess.exception(FIELD_WRITING_ERROR.getMessage(fieldName), e);
        }
    }

    public static @NotNull <T> T castObject(@NotNull Object objectToCast, @NotNull Class<T> castType) {
        if (isSubtypeOf(objectToCast, castType)) {
            return castType.cast(objectToCast);
        }
        throw ClassCast.exception(CANT_CAST_OBJECT.getMessage(objectToCast.getClass().getCanonicalName(), castType.getCanonicalName()));
    }

    /**
     * Проверяет что переданный экземпляр {@param inheritorInstance} является наследником класса {@param ancestorClass}
     * @param inheritorInstance экземпляр для проверки
     * @param ancestorClass    класс, по отношению к которому проводится проверка принадлежности
     * @return является ли экземпляр наследником переданного класса
     */
    public static boolean isSubtypeOf(@NotNull Object inheritorInstance, @NotNull Class<?> ancestorClass) {
        return isSubtypeOf(inheritorInstance.getClass(), ancestorClass);
    }

    /**
     * Проверка, что тип переданного поля является наследником от переданного класса.
     *
     * @param fieldToTest поле, тип которого нужно проверить
     * @param ancestorClass       класс, к которому проверяем приводимость - должен быть среди родителей
     * @return является ли поле наследником переданного класса
     */
    public static boolean isSubtypeOf(@NotNull Field fieldToTest, @NotNull Class<?> ancestorClass) {
        return isSubtypeOf(fieldToTest.getType(), ancestorClass);
    }

    /**
     * Проверяет что переданный класс {@param inheritorClass} является наследником класса {@param ancestorClass}
     * @param inheritorClass класс для проверки
     * @param ancestorClass класс, по отношению к которому проводится проверка принадлежности
     * @return является ли класс {@param inheritorClass} наследником класса {@param ancestorClass}
     */
    public static boolean isSubtypeOf(@NotNull Class<?> inheritorClass, @NotNull Class<?> ancestorClass) {
        return ancestorClass.isAssignableFrom(inheritorClass);
    }

    /**
     * TODO: JavaDoc
     */
    public static <T> Constructor<T> getConstructor(@NotNull Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            throw ConstructorNotFound.exception(CONSTRUCTOR_NOT_FOUND.getMessage(clazz.getCanonicalName(), parameterTypes));
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
    public static Field getAccessibleFieldWithInheritance(@NotNull Class<?> processedClass, @NotNull String fieldName) {
        Field targetField = getFieldsWithInheritance(processedClass)
                .stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> FieldNotFound.exception(FIELD_NOT_FOUND.getMessage(processedClass.getCanonicalName(), fieldName)));
        targetField.setAccessible(true);
        return targetField;
    }

    /**
     * Получить поля из объекта с учетом полей родителей
     * // TODO: Сделать сравнение по феншую
     * @param clazz тип объекта
     * @return список всех полей класса с учетом полей в родителях
     */
    public static List<Field> getFieldsWithInheritance(@NotNull Class<?> clazz) {
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
    public static <T> Deque<Class<? extends T>> getInheritedClasses(@NotNull Class<T> ancestorClass,
                                                                    @NotNull Class<? extends T> inheritorClass,
                                                                    @NotNull Order order) {
        Deque<Class<? extends T>> classChain = new ArrayDeque<>();
        Class<?> processedClass = inheritorClass;
        while (ancestorClass.isAssignableFrom(processedClass)) {
            if (order == Order.ASC) {
                classChain.addFirst((Class<T>) processedClass);
            }
            if (order == Order.DESC) {
                classChain.addLast((Class<T>) processedClass);
            }
            processedClass = processedClass.getSuperclass();
            if (Objects.isNull(processedClass)) {
                break;
            }
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
    public static <T> Deque<Class<? extends T>> getInheritedInterfaces(@NotNull Class<T> ancestorInterface,
                                                                       @NotNull Class<? extends T> inheritorClass,
                                                                       @NotNull Order order) {
        return getAllInheritedInterfaces(new ArrayDeque<>(), ancestorInterface, inheritorClass, order);
    }


    // TODO: Если класс экстендит другой класс, то интерфейсы не извлекаются
    protected static <T> Deque<Class<? extends T>> getAllInheritedInterfaces(@NotNull Deque<Class<? extends T>> inheritedInterfacesCollector,
                                                                             @NotNull Class<T> ancestorClass,
                                                                             @NotNull Class<? extends T> inheritorClass,
                                                                             @NotNull Order order) {
        Class<?>[] inheritedInterfaces = inheritorClass.getInterfaces();
        for (Class<?> inheritedInterface : inheritedInterfaces) {
            if (ancestorClass.isAssignableFrom(inheritedInterface) && !inheritedInterfacesCollector.contains(inheritedInterface)) {
                Class<? extends T> castedInheritedInterface = (Class<? extends T>) inheritedInterface;
                if (Order.DESC == order) {
                    inheritedInterfacesCollector.addLast(castedInheritedInterface);
                } else {
                    inheritedInterfacesCollector.addFirst(castedInheritedInterface);
                }
                getAllInheritedInterfaces(inheritedInterfacesCollector, ancestorClass, castedInheritedInterface, order);
            }
        }
        Class<?> superclass = inheritorClass.getSuperclass();
        if (Objects.nonNull(superclass) && ancestorClass.isAssignableFrom(superclass)) {
            Class<? extends T> castedSuperclass = (Class<? extends T>) superclass;
            getAllInheritedInterfaces(inheritedInterfacesCollector, ancestorClass, castedSuperclass, order);
        }
        return inheritedInterfacesCollector;
    }

    public enum Order {

        ASC,
        DESC

    }

}
