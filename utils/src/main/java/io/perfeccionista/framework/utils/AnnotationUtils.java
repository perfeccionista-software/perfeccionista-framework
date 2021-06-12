package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.measurements.Order;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findInheritedClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForMethods.findRequiredMethod;
import static io.perfeccionista.framework.utils.ReflectionUtilsForMethods.invokeMethod;
import static java.util.Arrays.asList;

public class AnnotationUtils {

    private static final Map<Class<? extends Annotation>, Boolean> repeatableAnnotationContainerCache = new ConcurrentHashMap<>(16);

    // Another annotation caches

    private AnnotationUtils() {
    }

    public static boolean isAnnotated(@NotNull AnnotatedElement element,
                                      @NotNull Class<? extends Annotation> annotationType) {
        return findAnnotation(element, annotationType).isPresent();
    }

    public static <A extends Annotation> Optional<A> findAnnotation(@NotNull AnnotatedElement element,
                                                                    @NotNull Class<A> annotationType) {
        boolean inherited = annotationType.isAnnotationPresent(Inherited.class);
        return findAnnotation(element, annotationType, inherited, new HashSet<>());
    }

    public static <A extends Annotation, T> Optional<A> findFirstAnnotationInHierarchy(@NotNull Class<A> annotationClass,
                                                                                       @NotNull Class<T> ancestorClass,
                                                                                       @NotNull Class<? extends T> inheritorClass) {
        return findInheritedClasses(ancestorClass, inheritorClass, Order.DESC).stream()
                .map(processedClass -> findAnnotation(processedClass, annotationClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private static <A extends Annotation> Optional<A> findAnnotation(@NotNull AnnotatedElement element,
                                                                     @NotNull Class<A> annotationType, boolean inherited, Set<Annotation> visited) {
        // Directly present?
        A annotation = element.getDeclaredAnnotation(annotationType);
        if (annotation != null) {
            return Optional.of(annotation);
        }

        // Meta-present on directly present annotations?
        Optional<A> directMetaAnnotation = findMetaAnnotation(annotationType, element.getDeclaredAnnotations(), inherited, visited);
        if (directMetaAnnotation.isPresent()) {
            return directMetaAnnotation;
        }

        if (element instanceof Class) {
            Class<?> clazz = (Class<?>) element;

            // Search on interfaces
            for (Class<?> ifc : clazz.getInterfaces()) {
                if (ifc != Annotation.class) {
                    Optional<A> annotationOnInterface = findAnnotation(ifc, annotationType, inherited, visited);
                    if (annotationOnInterface.isPresent()) {
                        return annotationOnInterface;
                    }
                }
            }

            // Indirectly present?
            // Search in class hierarchy
            if (inherited) {
                Class<?> superclass = clazz.getSuperclass();
                if (superclass != null && superclass != Object.class) {
                    Optional<A> annotationOnSuperclass = findAnnotation(superclass, annotationType, inherited, visited);
                    if (annotationOnSuperclass.isPresent()) {
                        return annotationOnSuperclass;
                    }
                }
            }
        }

        // Meta-present on indirectly present annotations?
        return findMetaAnnotation(annotationType, element.getAnnotations(), inherited, visited);
    }

    public static <A extends Annotation> List<A> findRepeatableAnnotations(@NotNull AnnotatedElement element, @NotNull Class<A> annotationType) {
        Repeatable repeatable = annotationType.getAnnotation(Repeatable.class);
        Class<? extends Annotation> containerType = repeatable.value();
        boolean inherited = containerType.isAnnotationPresent(Inherited.class);
        // We use a LinkedHashSet because the search algorithm may discover duplicates, but we need to maintain the original order.
        Set<A> found = new LinkedHashSet<>(16);
        findRepeatableAnnotations(element, annotationType, containerType, inherited, found, new HashSet<>(16));
        return Collections.unmodifiableList(new ArrayList<>(found));
    }

    public static <A extends Annotation> List<A> findAllRepeatableAnnotationsInHierarchy(@NotNull Class<A> annotationClass,
                                                                                         @NotNull Class<?> annotatedClass) {
        return findAllRepeatableAnnotationsInHierarchy(annotationClass, Object.class, annotatedClass);
    }

    public static <A extends Annotation, T> List<A> findAllRepeatableAnnotationsInHierarchy(@NotNull Class<A> annotationClass,
                                                                                            @NotNull Class<T> ancestorClass,
                                                                                            @NotNull Class<? extends T> inheritorClass) {
        List<A> annotationsList = new ArrayList<>();
        return findInheritedClasses(ancestorClass, inheritorClass, Order.ASC).stream()
                .map(processedClass -> findAllRepeatableAnnotationsInClassHierarchy(processedClass, annotationClass))
                .reduce(annotationsList, (previousElement, nextElement) -> {
                    previousElement.addAll(nextElement);
                    return previousElement;
                });
    }



    protected static <A extends Annotation, T> List<A> findAllRepeatableAnnotationsInClassHierarchy(@NotNull Class<T> processedClass,
                                                                                                    @NotNull Class<A> annotationClass) {
        List<A> annotations = new ArrayList<>(Arrays.asList(processedClass.getDeclaredAnnotationsByType(annotationClass)));
        Arrays.stream(processedClass.getInterfaces())
                .forEach(processedInterface -> annotations.addAll(Arrays.asList(processedInterface.getDeclaredAnnotationsByType(annotationClass))));
        return annotations;
    }

    private static <A extends Annotation> void findRepeatableAnnotations(@NotNull AnnotatedElement element, @NotNull Class<A> annotationType,
                                                                         @NotNull Class<? extends Annotation> containerType,
                                                                         boolean inherited, Set<A> found, Set<Annotation> visited) {
        if (element instanceof Class) {
            Class<?> clazz = (Class<?>) element;
            // Recurse first in order to support top-down semantics for inherited, repeatable annotations.
            if (inherited) {
                Class<?> superclass = clazz.getSuperclass();
                if (superclass != null && superclass != Object.class) {
                    findRepeatableAnnotations(superclass, annotationType, containerType, inherited, found, visited);
                }
            }
            // Search on interfaces
            for (Class<?> ifc : clazz.getInterfaces()) {
                if (ifc != Annotation.class) {
                    findRepeatableAnnotations(ifc, annotationType, containerType, inherited, found, visited);
                }
            }
        }
        // Find annotations that are directly present or meta-present on directly present annotations.
        findRepeatableAnnotations(element.getDeclaredAnnotations(), annotationType, containerType, inherited, found,
                visited);
        // Find annotations that are indirectly present or meta-present on indirectly present annotations.
        findRepeatableAnnotations(element.getAnnotations(), annotationType, containerType, inherited, found, visited);
    }

    private static <A extends Annotation> void findRepeatableAnnotations(Annotation[] candidates, Class<A> annotationType,
                                                                         Class<? extends Annotation> containerType,
                                                                         boolean inherited, Set<A> found, Set<Annotation> visited) {
        for (Annotation candidate : candidates) {
            Class<? extends Annotation> candidateAnnotationType = candidate.annotationType();
            if (!isInJavaLangAnnotationPackage(candidateAnnotationType) && visited.add(candidate)) {
                // Exact match?
                if (candidateAnnotationType.equals(annotationType)) {
                    found.add(annotationType.cast(candidate));
                }
                // Container?
                else if (candidateAnnotationType.equals(containerType)) {
                    // Note: it's not a legitimate containing annotation type if it doesn't declare
                    // a 'value' attribute that returns an array of the contained annotation type.
                    // See https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.3
                    Method method = findRequiredMethod(containerType, "value");
                    Annotation[] containedAnnotations = (Annotation[]) invokeMethod(method, candidate);
                    found.addAll((Collection<? extends A>) asList(containedAnnotations));
                }
                // Nested container annotation?
                else if (isRepeatableAnnotationContainer(candidateAnnotationType)) {
                    Method method = findRequiredMethod(candidateAnnotationType, "value");
                    Annotation[] containedAnnotations = (Annotation[]) invokeMethod(method, candidate);
                    for (Annotation containedAnnotation : containedAnnotations) {
                        findRepeatableAnnotations(containedAnnotation.getClass(), annotationType, containerType,
                                inherited, found, visited);
                    }
                }
                // Otherwise search recursively through the meta-annotation hierarchy...
                else {
                    findRepeatableAnnotations(candidateAnnotationType, annotationType, containerType, inherited, found,
                            visited);
                }
            }
        }
    }

    private static boolean isRepeatableAnnotationContainer(Class<? extends Annotation> candidateContainerType) {
        return repeatableAnnotationContainerCache.computeIfAbsent(candidateContainerType, candidate -> {
            // @formatter:off
            Repeatable repeatable = Arrays.stream(candidate.getMethods())
                    .filter(attribute -> attribute.getName().equals("value") && attribute.getReturnType().isArray())
                    .findFirst()
                    .map(attribute -> attribute.getReturnType().getComponentType().getAnnotation(Repeatable.class))
                    .orElse(null);
            // @formatter:on

            return repeatable != null && candidate.equals(repeatable.value());
        });
    }

    private static <A extends Annotation> Optional<A> findMetaAnnotation(Class<A> annotationType, Annotation[] candidates,
                                                                         boolean inherited, Set<Annotation> visited) {
        for (Annotation candidateAnnotation : candidates) {
            Class<? extends Annotation> candidateAnnotationType = candidateAnnotation.annotationType();
            if (!isInJavaLangAnnotationPackage(candidateAnnotationType) && visited.add(candidateAnnotation)) {
                Optional<A> metaAnnotation = findAnnotation(candidateAnnotationType, annotationType, inherited,
                        visited);
                if (metaAnnotation.isPresent()) {
                    return metaAnnotation;
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isInJavaLangAnnotationPackage(Class<? extends Annotation> annotationType) {
        return (annotationType != null && annotationType.getName().startsWith("java.lang.annotation"));
    }

}
