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

public class DataSourceHolder<T extends DataSource<?, ?>> {

    private static final Map<Class<? extends DataSource<?, ?>>, Set<String>> annotationNameCache = new HashMap<>();

    private final Class<T> dataSourceClass;
    private final Class<T> dataSourceImplementation;
    private final Set<String> names;

    private DataSourceHolder(Class<T> dataSourceClass,
                             Class<T> dataSourceImplementation,
                             Set<String> names) {
        this.dataSourceClass = dataSourceClass;
        this.dataSourceImplementation = dataSourceImplementation;
        this.names = names;
    }

    public static <T extends DataSource<?, ?>> DataSourceHolder<T> of(@NotNull Class<T> dataSourceClass,
                                                                      String... additionalNames) {
        return DataSourceHolder.of(dataSourceClass, dataSourceClass, additionalNames);
    }

    public static <T extends DataSource<?, ?>> DataSourceHolder<T> of(@NotNull Class<T> dataSourceClass,
                                                                      @NotNull Class<T> dataSourceImplementation,
                                                                      String... additionalNames) {
        if (!annotationNameCache.containsKey(dataSourceClass)) {
            extractNames(dataSourceClass);
        }
        Set<String> names = new HashSet<>();
        names.addAll(annotationNameCache.get(dataSourceClass));
        names.addAll(Arrays.stream(additionalNames).collect(toSet()));
        return new DataSourceHolder<>(dataSourceClass, dataSourceImplementation, names);
    }

    public Class<T> getDataSourceClass() {
        return dataSourceClass;
    }

    public Class<T> getDataSourceImplementation() {
        return dataSourceImplementation;
    }

    public T getDataSourceInstance() {
        return newInstance(dataSourceImplementation);
    }

    public Set<String> getNames() {
        return names;
    }

    public boolean containsName(String name) {
        return names.contains(name);
    }

    private static synchronized <T extends DataSource<?, ?>> void extractNames(Class<T> dataSourceClass) {
        List<Name> names = findRepeatableAnnotations(dataSourceClass, Name.class);
        if (names.isEmpty()) {
            annotationNameCache.put(dataSourceClass, Set.of());
        } else {
            annotationNameCache.put(dataSourceClass, names.stream().map(Name::value).collect(toSet()));
        }
    }

}
