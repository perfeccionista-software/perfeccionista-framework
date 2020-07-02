package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.utils.ReflectionUtils.getClassInheritors;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class AnnotationUtils {

    public static <A extends Annotation, T> Optional<A> findFirstAnnotationInHierarchy(@NotNull Class<A> annotationClass,
                                                                                       @NotNull Class<T> ancestorClass,
                                                                                       @NotNull Class<? extends T> inheritorClass) {
        return getClassInheritors(ancestorClass, inheritorClass, Order.DESC).stream()
                .map(processedClass -> findAnnotation(processedClass, annotationClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public static <A extends Annotation, T> List<A> findAllRepeatableAnnotationsInHierarchy(@NotNull Class<A> annotationClass,
                                                                                            @NotNull Class<T> ancestorClass,
                                                                                            @NotNull Class<? extends T> inheritorClass) {
        List<A> annotationsList = new ArrayList<>();
        return getClassInheritors(ancestorClass, inheritorClass, Order.DESC).stream()
                .map(processedClass -> findAllRepeatableAnnotationsInClassHierarchy(processedClass, annotationClass))
                .reduce(annotationsList, (previousElement, nextElement) -> {
                    previousElement.addAll(nextElement);
                    return previousElement;
                });
    }

    protected static <A extends Annotation, T> List<A> findAllRepeatableAnnotationsInClassHierarchy(Class<T> processedClass, Class<A> annotationClass) {
        List<A> annotations = new ArrayList<>(Arrays.asList(processedClass.getDeclaredAnnotationsByType(annotationClass)));
        Arrays.stream(processedClass.getInterfaces())
                .forEach(processedInterface -> annotations.addAll(Arrays.asList(processedInterface.getDeclaredAnnotationsByType(annotationClass))));
        return annotations;
    }

}