package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;
import static java.util.stream.Collectors.toSet;

public class DataConverterHolder<T extends DataConverter<?, ?>> {

    private static final Map<Class<? extends DataConverter<?, ?>>, Set<String>> annotationNameCache = new HashMap<>();

    private final Class<T> dataConverterClass;
    private final Class<T> dataConverterImplementation;
    private final Set<String> names;

    private DataConverterHolder(Class<T> dataConverterClass,
                                Class<T> dataConverterImplementation,
                                Set<String> names) {
        this.dataConverterClass = dataConverterClass;
        this.dataConverterImplementation = dataConverterImplementation;
        this.names = names;
    }

    public static <T extends DataConverter<?, ?>> DataConverterHolder<T> of(@NotNull Class<T> dataConverterClass,
                                                                            String... additionalNames) {
        return DataConverterHolder.of(dataConverterClass, dataConverterClass, additionalNames);
    }

    public static <T extends DataConverter<?, ?>> DataConverterHolder<T> of(@NotNull Class<T> dataConverterClass,
                                                                            @NotNull Class<T> dataConverterImplementation,
                                                                            String... additionalNames) {
        if (!annotationNameCache.containsKey(dataConverterClass)) {
            extractNames(dataConverterClass);
        }
        Set<String> names = new HashSet<>();
        names.addAll(annotationNameCache.get(dataConverterClass));
        names.addAll(Arrays.stream(additionalNames).collect(toSet()));
        return new DataConverterHolder<>(dataConverterClass, dataConverterImplementation, names);
    }

    public Class<T> getDataConverterClass() {
        return dataConverterClass;
    }

    public Class<T> getDataConverterImplementation() {
        return dataConverterImplementation;
    }

    public T getDataConverterInstance() {
        return newInstance(dataConverterImplementation);
    }

    public Set<String> getNames() {
        return names;
    }

    public boolean containsName(String name) {
        return names.contains(name);
    }

    private static synchronized <T extends DataConverter<?, ?>> void extractNames(Class<T> dataConverterClass) {
        List<Name> names = findRepeatableAnnotations(dataConverterClass, Name.class);
        if (names.isEmpty()) {
            annotationNameCache.put(dataConverterClass, Set.of());
        } else {
            annotationNameCache.put(dataConverterClass, names.stream().map(Name::value).collect(toSet()));
        }
    }

}
