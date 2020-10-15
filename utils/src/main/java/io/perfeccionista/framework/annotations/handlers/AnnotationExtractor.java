package io.perfeccionista.framework.annotations.handlers;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

// TODO: Выполнять весь поиск аннотаций этими методами. Должны возвращаться аннотации из Inherited аннотаций
public class AnnotationExtractor {

    private AnnotationExtractor() {}

    public static <T extends Annotation> Optional<T> findFirstInHierarchy(Class<T> annotationClass, Deque<Class<?>> annotatedClasses) {
        Optional<Class<?>> optionalAnnotatedClass = annotatedClasses.stream()
                .filter(processedClass -> isAnnotated(processedClass, annotationClass))
                .findFirst();
        if (optionalAnnotatedClass.isPresent()) {
            return Optional.ofNullable(optionalAnnotatedClass.get().getDeclaredAnnotation(annotationClass));
        }
        return Optional.empty();
    }

    public static <T extends Annotation> List<T> findAllInHierarchy(Class<T> annotationClass, Deque<Class<?>> annotatedClasses) {
        List<T> annotations = new ArrayList<>();
        annotatedClasses.stream()
                .filter(processedClass -> isAnnotated(processedClass, annotationClass))
                .forEach(annotatedClass -> {
                    annotations.addAll(Arrays.asList(annotatedClass.getDeclaredAnnotationsByType(annotationClass)));
                });
        return annotations;
    }

}
