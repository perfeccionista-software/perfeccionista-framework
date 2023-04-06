package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AndroidReflectionUtilsForClasses {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtilsForClasses.class);

    private static final Map<String, List<Class<?>>> classesByPackage = new ConcurrentHashMap<>(64);
    private static final Map<String, Class<?>> classesByName = new ConcurrentHashMap<>(64);

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






    private static synchronized void fillClassesByPackagesMap(@NotNull String packageName) {
//        if (!classesByPackage.containsKey(packageName)) {
//            List<Class<?>> classesForUris = new ClassPathScanner()
//                    .findClassesForUris(packageName, ClassFilter.empty());
//            classesByPackage.put(packageName, classesForUris);
//        }
    }

}
