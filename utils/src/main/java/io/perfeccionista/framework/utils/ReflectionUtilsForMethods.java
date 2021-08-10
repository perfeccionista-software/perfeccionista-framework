package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.MethodInvocationFailed;
import io.perfeccionista.framework.exceptions.MethodNotFound;
import io.perfeccionista.framework.exceptions.PreconditionViolation;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.METHOD_INVOCATION_FAILED;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.METHOD_NOT_FOUND;
import static io.perfeccionista.framework.utils.CastUtils.isAbstract;
import static io.perfeccionista.framework.utils.ReflectionUtils.makeAccessible;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class ReflectionUtilsForMethods {

    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];

    private ReflectionUtilsForMethods() {
    }

    public static boolean isMethodPresent(@NotNull Class<?> clazz, @NotNull Predicate<Method> predicate) {
        return findMethod(clazz, predicate).isPresent();
    }


    public static Object invokeMethod(@NotNull Method method, @NotNull Object target, Object... args) {
        try {
            return makeAccessible(method).invoke(target, args);
        } catch (Throwable t) {
            throw MethodInvocationFailed.exception(METHOD_INVOCATION_FAILED.getMessage(method.getName()), t);
        }
    }

    public static Object invokeStaticMethod(@NotNull Method method, Object... args) {
        try {
            return makeAccessible(method).invoke(null, args);
        } catch (Throwable t) {
            throw MethodInvocationFailed.exception(METHOD_INVOCATION_FAILED.getMessage(method.getName()), t);
        }
    }

    public static Method findRequiredMethod(@NotNull Class<?> clazz, @NotNull String methodName, @NotNull Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw MethodNotFound.exception(METHOD_NOT_FOUND.getMessage(methodName), e);
        }
    }

    public static Optional<Method> findMethod(@NotNull Class<?> clazz, @NotNull String methodName, @NotNull Class<?>... parameterTypes) {
        Arrays.stream(parameterTypes)
                .forEach(object -> {
                    if (isNull(object)) {
                        throw PreconditionViolation.exception("Parameter types must not be null");
                    }
                });
        return findMethod(clazz, method -> hasCompatibleSignature(method, methodName, parameterTypes));
    }

    public static Optional<Method> findMethod(@NotNull Class<?> clazz, @NotNull String methodName, String parameterTypeNames) {
        return findMethod(clazz, methodName, resolveParameterTypes(parameterTypeNames));
    }

    private static Optional<Method> findMethod(@NotNull Class<?> clazz, @NotNull Predicate<Method> predicate) {
        for (Class<?> current = clazz; isSearchable(current); current = current.getSuperclass()) {
            // Search for match in current type
            List<Method> methods = current.isInterface() ? getClassMethods(current) : getDeclaredMethods(current, HierarchyTraversalMode.BOTTOM_UP);
            for (Method method : methods) {
                if (predicate.test(method)) {
                    return Optional.of(method);
                }
            }
            // Search for match in interfaces implemented by current type
            for (Class<?> ifc : current.getInterfaces()) {
                Optional<Method> optional = findMethod(ifc, predicate);
                if (optional.isPresent()) {
                    return optional;
                }
            }
        }
        return Optional.empty();
    }

    public static List<Method> findMethods(@NotNull Class<?> clazz, @NotNull Predicate<Method> predicate) {
        return findMethods(clazz, predicate, HierarchyTraversalMode.TOP_DOWN);
    }

    public static List<Method> findMethods(@NotNull Class<?> clazz, @NotNull Predicate<Method> predicate, @NotNull HierarchyTraversalMode traversalMode) {
        return Collections.unmodifiableList(findAllMethodsInHierarchy(clazz, traversalMode).stream()
                .filter(predicate)
                .collect(toList()));
    }

    private static List<Method> findAllMethodsInHierarchy(Class<?> clazz, HierarchyTraversalMode traversalMode) {
        List<Method> localMethods = getDeclaredMethods(clazz, traversalMode).stream()
                .filter(method -> !method.isSynthetic())
                .collect(toList());
        List<Method> superclassMethods = getSuperclassMethods(clazz, traversalMode).stream()
                .filter(method -> !isMethodShadowedByLocalMethods(method, localMethods))
                .collect(toList());
        List<Method> interfaceMethods = getInterfaceMethods(clazz, traversalMode).stream()
                .filter(method -> !isMethodShadowedByLocalMethods(method, localMethods))
                .collect(toList());

        List<Method> methods = new ArrayList<>();
        if (traversalMode == HierarchyTraversalMode.TOP_DOWN) {
            methods.addAll(superclassMethods);
            methods.addAll(interfaceMethods);
        }
        methods.addAll(localMethods);
        if (traversalMode == HierarchyTraversalMode.BOTTOM_UP) {
            methods.addAll(interfaceMethods);
            methods.addAll(superclassMethods);
        }
        return methods;
    }

    private static List<Method> getClassMethods(Class<?> clazz) {
        return toSortedMutableList(clazz.getMethods());
    }

    private static List<Method> getDeclaredMethods(Class<?> clazz, HierarchyTraversalMode traversalMode) {
        List<Method> defaultMethods = getDefaultMethods(clazz);
        List<Method> declaredMethods = toSortedMutableList(clazz.getDeclaredMethods());
        // Take the traversal mode into account in order to retain the inherited
        // nature of interface default methods.
        if (traversalMode == HierarchyTraversalMode.BOTTOM_UP) {
            declaredMethods.addAll(defaultMethods);
            return declaredMethods;
        }
        else {
            defaultMethods.addAll(declaredMethods);
            return defaultMethods;
        }
    }

    private static List<Method> getDefaultMethods(Class<?> clazz) {
        List<Method> visibleDefaultMethods = Arrays.stream(clazz.getMethods())
                .filter(Method::isDefault)
                .collect(toCollection(ArrayList::new));
        if (visibleDefaultMethods.isEmpty()) {
            return visibleDefaultMethods;
        }
        return Arrays.stream(clazz.getInterfaces())
                .map(ReflectionUtilsForMethods::getClassMethods)
                .flatMap(List::stream)
                .filter(visibleDefaultMethods::contains)
                .collect(toCollection(ArrayList::new));
    }

    private static List<Method> getInterfaceMethods(Class<?> clazz, HierarchyTraversalMode traversalMode) {
        List<Method> allInterfaceMethods = new ArrayList<>();
        for (Class<?> ifc : clazz.getInterfaces()) {
            List<Method> localInterfaceMethods = getClassMethods(ifc).stream()
                    .filter(m -> !isAbstract(m))
                    .collect(toList());
            List<Method> superinterfaceMethods = getInterfaceMethods(ifc, traversalMode).stream()
                    .filter(method -> !isMethodShadowedByLocalMethods(method, localInterfaceMethods))
                    .collect(toList());
            if (traversalMode == HierarchyTraversalMode.TOP_DOWN) {
                allInterfaceMethods.addAll(superinterfaceMethods);
            }
            allInterfaceMethods.addAll(localInterfaceMethods);
            if (traversalMode == HierarchyTraversalMode.BOTTOM_UP) {
                allInterfaceMethods.addAll(superinterfaceMethods);
            }
        }
        return allInterfaceMethods;
    }

    private static List<Method> getSuperclassMethods(Class<?> clazz, HierarchyTraversalMode traversalMode) {
        Class<?> superclass = clazz.getSuperclass();
        if (!isSearchable(superclass)) {
            return Collections.emptyList();
        }
        return findAllMethodsInHierarchy(superclass, traversalMode);
    }

    private static boolean isMethodShadowedByLocalMethods(Method method, List<Method> localMethods) {
        return localMethods.stream().anyMatch(local -> isMethodShadowedBy(method, local));
    }

    private static boolean isMethodShadowedBy(Method upper, Method lower) {
        return hasCompatibleSignature(upper, lower.getName(), lower.getParameterTypes());
    }

    private static boolean hasCompatibleSignature(Method candidate, String methodName, Class<?>[] parameterTypes) {
        if (!methodName.equals(candidate.getName())) {
            return false;
        }
        if (parameterTypes.length != candidate.getParameterCount()) {
            return false;
        }
        // trivial case: parameter types exactly match
        if (Arrays.equals(parameterTypes, candidate.getParameterTypes())) {
            return true;
        }
        // param count is equal, but types do not match exactly: check for method sub-signatures
        // https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.2
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> lowerType = parameterTypes[i];
            Class<?> upperType = candidate.getParameterTypes()[i];
            if (!upperType.isAssignableFrom(lowerType)) {
                return false;
            }
        }
        // lower is sub-signature of upper: check for generics in upper method
        if (isGeneric(candidate)) {
            return true;
        }
        return false;
    }

    private static boolean isGeneric(Method method) {
        return isGeneric(method.getGenericReturnType())
                || Arrays.stream(method.getGenericParameterTypes()).anyMatch(ReflectionUtilsForMethods::isGeneric);
    }

    private static boolean isGeneric(Type type) {
        return type instanceof TypeVariable || type instanceof GenericArrayType;
    }

    private static boolean isSearchable(Class<?> clazz) {
        return (clazz != null && clazz != Object.class);
    }

    private static List<Method> toSortedMutableList(Method[] methods) {
        return Arrays.stream(methods)
                .sorted(ReflectionUtilsForMethods::defaultMethodSorter)
                .collect(toCollection(ArrayList::new));
    }

    private static int defaultFieldSorter(Field field1, Field field2) {
        return Integer.compare(field1.getName().hashCode(), field2.getName().hashCode());
    }

    private static int defaultMethodSorter(Method method1, Method method2) {
        String name1 = method1.getName();
        String name2 = method2.getName();
        int comparison = Integer.compare(name1.hashCode(), name2.hashCode());
        if (comparison == 0) {
            comparison = name1.compareTo(name2);
            if (comparison == 0) {
                comparison = method1.toString().compareTo(method2.toString());
            }
        }
        return comparison;
    }

    private static Class<?>[] resolveParameterTypes(String parameterTypeNames) {
        if (StringUtils.isBlank(parameterTypeNames)) {
            return EMPTY_CLASS_ARRAY;
        }
        return Arrays.stream(parameterTypeNames.split(","))
                .map(String::trim)
                .map(ReflectionUtilsForClasses::loadClass)
                .toArray(Class[]::new);
    }

    public enum HierarchyTraversalMode {
        TOP_DOWN,
        BOTTOM_UP;
    }

}
