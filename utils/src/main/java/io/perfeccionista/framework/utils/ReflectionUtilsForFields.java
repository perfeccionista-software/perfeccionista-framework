package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FieldAccess;
import io.perfeccionista.framework.exceptions.FieldNotFound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_READING_ERROR;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FIELD_WRITING_ERROR;

public class ReflectionUtilsForFields {

    private ReflectionUtilsForFields() {
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
            Field targetField = findAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
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
            Field targetField = findAccessibleFieldWithInheritance(parentElement.getClass(), fieldName);
            targetField.set(parentElement, value);
            return parentElement;
        } catch (IllegalAccessException e) {
            throw FieldAccess.exception(FIELD_WRITING_ERROR.getMessage(fieldName), e);
        }
    }

    /**
     * Получить поле из объекта с учетом полей родителей
     *
     * @param processedClass     тип объекта
     * @param fieldName имя поля
     * @return поле, объявленное доступным
     */
    public static Field findAccessibleFieldWithInheritance(@NotNull Class<?> processedClass, @NotNull String fieldName) {
        Field targetField = findAllFieldsWithInheritance(processedClass)
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
    public static List<Field> findAllFieldsWithInheritance(@NotNull Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        } while (!"java.lang.Object".equals(clazz.getName()));
        return fields;
    }

}
