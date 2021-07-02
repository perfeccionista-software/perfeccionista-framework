package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.ClassCanNotBeCast;
import io.perfeccionista.framework.exceptions.ClassCanNotBeInstantiated;
import io.perfeccionista.framework.exceptions.ClassNotFound;
import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.measurements.Order;
import io.perfeccionista.framework.utils.models.ClassFilter;
import io.perfeccionista.framework.utils.models.ClasspathScanner;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.ConstructorNotFound;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CREATE_OBJECT;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CREATE_OBJECT_WITH_CONSTRUCTOR;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_LOAD_CLASS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CONSTRUCTOR_NOT_FOUND;
import static io.perfeccionista.framework.utils.ReflectionUtils.makeAccessible;
import static io.perfeccionista.framework.utils.StringUtils.objectTypesToString;
import static java.util.stream.Collectors.toList;

// TODO: Отредактировать утилитные классы и джавадоки
public final class ReflectionUtilsForClasses {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtilsForClasses.class);

    private static final Map<String, List<Class<?>>> classesByPackage = new ConcurrentHashMap<>(64);
    private static final Map<String, Class<?>> classesByName = new ConcurrentHashMap<>(64);

    private static final Pattern VM_INTERNAL_OBJECT_ARRAY_PATTERN = Pattern.compile("^(\\[+)L(.+);$");
    // Pattern: "[x", "[[[[x", etc., where x is Z, B, C, D, F, I, J, S, etc.
    private static final Pattern VM_INTERNAL_PRIMITIVE_ARRAY_PATTERN = Pattern.compile("^(\\[+)(\\[[ZBCDFIJS])$");
    // Pattern: "java.lang.String[]", "int[]", "int[][][][]", etc.
    private static final Pattern SOURCE_CODE_SYNTAX_ARRAY_PATTERN = Pattern.compile("^([^\\[\\]]+)((\\[\\])+)+$");
    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];

    static {
        // @formatter:off
        List<Class<?>> commonTypes = Arrays.asList(
                boolean.class,
                byte.class,
                char.class,
                short.class,
                int.class,
                long.class,
                float.class,
                double.class,

                boolean[].class,
                byte[].class,
                char[].class,
                short[].class,
                int[].class,
                long[].class,
                float[].class,
                double[].class,

                boolean[][].class,
                byte[][].class,
                char[][].class,
                short[][].class,
                int[][].class,
                long[][].class,
                float[][].class,
                double[][].class,

                Boolean.class,
                Byte.class,
                Character.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                String.class,

                Boolean[].class,
                Byte[].class,
                Character[].class,
                Short[].class,
                Integer[].class,
                Long[].class,
                Float[].class,
                Double[].class,
                String[].class,

                Boolean[][].class,
                Byte[][].class,
                Character[][].class,
                Short[][].class,
                Integer[][].class,
                Long[][].class,
                Float[][].class,
                Double[][].class,
                String[][].class
        );

        commonTypes.forEach(type -> {
            classesByName.put(type.getName(), type);
            classesByName.put(type.getCanonicalName(), type);
        });

    }

    private ReflectionUtilsForClasses() {
    }

    // get Constructor

    /**
     * TODO: JavaDoc
     */
    public static <T> Constructor<T> getConstructor(@NotNull Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            throw ConstructorNotFound.exception(CONSTRUCTOR_NOT_FOUND.getMessage(clazz.getCanonicalName(), parameterTypes), e);
        }
    }

    /**
     * TODO: JavaDoc
     */
    public static <T> Constructor<T> getDeclaredConstructor(@NotNull Class<T> clazz) {
        try {
            List<Constructor<?>> constructors = Arrays.stream(clazz.getDeclaredConstructors())//
                    .filter(ctor -> !ctor.isSynthetic())//
                    .collect(toList());
            if (constructors.size() != 1) {
                throw PreconditionViolation.exception(String.format("Class '%s' must declare a single constructor", clazz.getCanonicalName()));
            }
            return (Constructor<T>) constructors.get(0);
        } catch (Throwable t) {
            throw ConstructorNotFound.exception(CONSTRUCTOR_NOT_FOUND.getMessage(clazz.getCanonicalName()), t);
        }
    }

    // new Instance

    public static <T> T newInstance(@NotNull Class<T> clazz, @NotNull Object... args) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
            return newInstance(clazz.getDeclaredConstructor(parameterTypes), args);
        } catch (Throwable t) {
            throw ClassCanNotBeInstantiated.exception(CANT_CREATE_OBJECT.getMessage(clazz.getCanonicalName(), objectTypesToString(args)));
        }
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... args) {
        try {
            return makeAccessible(constructor).newInstance(args);
        } catch (Throwable t) {
            throw ClassCanNotBeInstantiated.exception(CANT_CREATE_OBJECT_WITH_CONSTRUCTOR.getMessage(constructor.toString(), objectTypesToString(args)));
        }
    }

    // Class loading

    public static Class<?> loadClass(@NotNull String className) {
        return loadClass(className, getDefaultClassLoader());
    }

    public static <T> Class<? extends T> loadClass(@NotNull String className, @NotNull Class<T> type) {
        Class<?> loadedClass = loadClass(className);
        if (type.isAssignableFrom(loadedClass)) {
            return (Class<? extends T>) loadedClass;
        }
        throw ClassCanNotBeCast.exception(CANT_CAST_OBJECT.getMessage(loadedClass.getCanonicalName(), type.getCanonicalName()));
    }

    public static Class<?> loadClass(@NotNull String className, @NotNull ClassLoader classLoader) {
        String trimmedName = className.trim();
        if (classesByName.containsKey(trimmedName)) {
            return classesByName.get(trimmedName);
        }
        Matcher matcher;
        // Primitive arrays such as "[I", "[[[[D", etc.
        matcher = VM_INTERNAL_PRIMITIVE_ARRAY_PATTERN.matcher(trimmedName);
        if (matcher.matches()) {
            String brackets = matcher.group(1);
            String componentTypeName = matcher.group(2);
            // Calculate dimensions by counting brackets.
            int dimensions = brackets.length();
            return loadArrayType(classLoader, componentTypeName, dimensions);
        }
        // Object arrays such as "[Ljava.lang.String;", "[[[[Ljava.lang.String;", etc.
        matcher = VM_INTERNAL_OBJECT_ARRAY_PATTERN.matcher(trimmedName);
        if (matcher.matches()) {
            String brackets = matcher.group(1);
            String componentTypeName = matcher.group(2);
            // Calculate dimensions by counting brackets.
            int dimensions = brackets.length();
            return loadArrayType(classLoader, componentTypeName, dimensions);
        }
        // Arrays such as "java.lang.String[]", "int[]", "int[][][][]", etc.
        matcher = SOURCE_CODE_SYNTAX_ARRAY_PATTERN.matcher(trimmedName);
        if (matcher.matches()) {
            String componentTypeName = matcher.group(1);
            String bracketPairs = matcher.group(2);
            // Calculate dimensions by counting bracket pairs.
            int dimensions = bracketPairs.length() / 2;
            return loadArrayType(classLoader, componentTypeName, dimensions);
        }
        Class<?> loadedClass = tryToLoadClass(trimmedName, classLoader);
        classesByName.put(loadedClass.getCanonicalName(), loadedClass);
        return loadedClass;
    }

    public static ClassLoader getDefaultClassLoader() {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
        } catch (OutOfMemoryError error) {
            throw error;
        } catch (Throwable t) {
            /* ignore */
        }
        return ClassLoader.getSystemClassLoader();
    }

    protected static Class<?> tryToLoadClass(@NotNull String className, @NotNull ClassLoader classLoader) {
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw ClassNotFound.exception(CANT_LOAD_CLASS.getMessage(className), e);
        }
    }

    private static Class<?> loadArrayType(ClassLoader classLoader, String componentTypeName, int dimensions) {
        Class<?> componentType = classesByName.containsKey(componentTypeName)
                ? classesByName.get(componentTypeName)
                : tryToLoadClass(componentTypeName, classLoader);

        return Array.newInstance(componentType, new int[dimensions]).getClass();

    }

    // Class search

    public static <T> Set<Class<? extends T>> findAllClasses(@NotNull Set<String> packageNames, @NotNull Class<T> type) {
        //noinspection unchecked
        return new HashSet<>(packageNames.stream()
                .map(packageName -> {
                    fillClassesByPackagesMap(packageName.trim());
                    return classesByPackage.get(packageName.trim()).stream()
                            .filter(type::isAssignableFrom)
                            .map(processedClass -> (Class<T>) processedClass)
                            .collect(Collectors.toList());
                })
                .reduce(new ArrayList<>(), (lastClassesSet, processedClassesSet) -> {
                    lastClassesSet.addAll(processedClassesSet);
                    return lastClassesSet;
                }));
    }

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
    public static <T> Deque<Class<? extends T>> findInheritedClasses(@NotNull Class<T> ancestorClass,
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
    public static <T> Deque<Class<? extends T>> findInheritedInterfaces(@NotNull Class<T> ancestorInterface,
                                                                        @NotNull Class<? extends T> inheritorClass,
                                                                        @NotNull Order order) {
        return findAllInheritedInterfaces(new ArrayDeque<>(), ancestorInterface, inheritorClass, order);
    }


    // TODO: Если класс экстендит другой класс, то интерфейсы не извлекаются
    protected static <T> Deque<Class<? extends T>> findAllInheritedInterfaces(@NotNull Deque<Class<? extends T>> inheritedInterfacesCollector,
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
                findAllInheritedInterfaces(inheritedInterfacesCollector, ancestorClass, castedInheritedInterface, order);
            }
        }
        Class<?> superclass = inheritorClass.getSuperclass();
        if (Objects.nonNull(superclass) && ancestorClass.isAssignableFrom(superclass)) {
            Class<? extends T> castedSuperclass = (Class<? extends T>) superclass;
            findAllInheritedInterfaces(inheritedInterfacesCollector, ancestorClass, castedSuperclass, order);
        }
        return inheritedInterfacesCollector;
    }

    public static Optional<Type> findGenericInterface(@NotNull Type inheritorType, @NotNull Class<?> ancestorInterface) {
        if (inheritorType instanceof ParameterizedType) {
            ParameterizedType inheritorParametrizedType = (ParameterizedType) inheritorType;
            Class<?> rawInheritorType = (Class<?>) inheritorParametrizedType.getRawType();
            if (rawInheritorType.equals(ancestorInterface)) {
                return Optional.of(inheritorParametrizedType);
            }
            for (Type type : rawInheritorType.getGenericInterfaces()) {
                Optional<Type> genericInterface = findGenericInterface(type, ancestorInterface);
                if (genericInterface.isPresent()) {
                    return genericInterface;
                }
            }
        } else {
            Class<?> inheritorClass = (Class<?>) inheritorType;
            if (inheritorClass.equals(ancestorInterface)) {
                return Optional.of(inheritorType);
            }
            for (Type type : inheritorClass.getGenericInterfaces()) {
                Optional<Type> genericInterface = findGenericInterface(type, ancestorInterface);
                if (genericInterface.isPresent()) {
                    return genericInterface;
                }
            }
        }
        return Optional.empty();
    }

    private static synchronized void fillClassesByPackagesMap(@NotNull String packageName) {
        if (!classesByPackage.containsKey(packageName)) {
            List<Class<?>> classesForUris = new ClasspathScanner(getDefaultClassLoader())
                    .findClassesForUris(packageName, ClassFilter.empty());
            classesByPackage.put(packageName, classesForUris);
        }
    }



}
