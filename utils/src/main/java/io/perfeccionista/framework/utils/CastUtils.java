package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.ClassCanNotBeCast;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;

public class CastUtils {

    private CastUtils() {
    }

    public static boolean isPublic(@NotNull Class<?> clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }

    public static boolean isPublic(@NotNull Member member) {
        return Modifier.isPublic(member.getModifiers());
    }

    public static boolean isPrivate(@NotNull Class<?> clazz) {
        return Modifier.isPrivate(clazz.getModifiers());
    }

    public static boolean isPrivate(@NotNull Member member) {
        return Modifier.isPrivate(member.getModifiers());
    }

    public static boolean isNotPrivate(@NotNull Class<?> clazz) {
        return !isPrivate(clazz);
    }

    public static boolean isNotPrivate(@NotNull Member member) {
        return !isPrivate(member);
    }

    public static boolean isAbstract(@NotNull Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    public static boolean isAbstract(@NotNull Member member) {
        return Modifier.isAbstract(member.getModifiers());
    }

    public static boolean isStatic(@NotNull Class<?> clazz) {
        return Modifier.isStatic(clazz.getModifiers());
    }

    public static boolean isStatic(@NotNull Member member) {
        return Modifier.isStatic(member.getModifiers());
    }

    public static boolean isNotStatic(@NotNull Class<?> clazz) {
        return !isStatic(clazz);
    }

    public static boolean isNotStatic(@NotNull Member member) {
        return !isStatic(member);
    }

    public static boolean isFinal(@NotNull Class<?> clazz) {
        return Modifier.isFinal(clazz.getModifiers());
    }

    public static boolean isFinal(@NotNull Member member) {
        return Modifier.isFinal(member.getModifiers());
    }

    public static boolean isNotFinal(@NotNull Class<?> clazz) {
        return !isFinal(clazz);
    }

    public static boolean isNotFinal(@NotNull Member member) {
        return !isFinal(member);
    }

    public static boolean isInnerClass(@NotNull Class<?> clazz) {
        return !isStatic(clazz) && clazz.isMemberClass();
    }

    public static boolean returnsVoid(@NotNull Method method) {
        return method.getReturnType().equals(Void.TYPE);
    }

    public static boolean isArray(@NotNull Object obj) {
        return (obj != null && obj.getClass().isArray());
    }

    /**
     * Проверяет что переданный экземпляр {@param inheritorInstance} является наследником класса {@param ancestorClass}
     * @param inheritorInstance экземпляр для проверки
     * @param ancestorClass     класс, по отношению к которому проводится проверка принадлежности
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

    public static @NotNull <T> T castObject(@NotNull Object objectToCast, @NotNull Class<T> castType) {
        if (isSubtypeOf(objectToCast, castType)) {
            return castType.cast(objectToCast);
        }
        throw ClassCanNotBeCast.exception(CANT_CAST_OBJECT.getMessage(objectToCast.getClass().getCanonicalName(), castType.getCanonicalName()));
    }




}
