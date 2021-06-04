package io.perfeccionista.framework.utils.models;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class ClassFilter implements Predicate<Class<?>> {

    /**
     * Create a {@link ClassFilter} instance that accepts all names and classes.
     */
    public static ClassFilter empty() {
        return of(name -> true, clazz -> true);
    }

    /**
     * Create a {@link ClassFilter} instance that accepts all names but filters classes.
     */
    public static ClassFilter of(Predicate<Class<?>> classPredicate) {
        return of(name -> true, classPredicate);
    }

    /**
     * Create a {@link ClassFilter} instance that filters by names and classes.
     */
    public static ClassFilter of(Predicate<String> namePredicate, Predicate<Class<?>> classPredicate) {
        return new ClassFilter(namePredicate, classPredicate);
    }

    private final Predicate<String> namePredicate;
    private final Predicate<Class<?>> classPredicate;

    private ClassFilter(@NotNull Predicate<String> namePredicate, @NotNull Predicate<Class<?>> classPredicate) {
        this.namePredicate = namePredicate;
        this.classPredicate = classPredicate;
    }

    /**
     * Test name using the stored name predicate.
     */
    public boolean match(String name) {
        return namePredicate.test(name);
    }

    /**
     * Test class using the stored class predicate.
     */
    public boolean match(Class<?> type) {
        return classPredicate.test(type);
    }

    /**
     * @implNote This implementation combines all tests stored in the predicates
     * of this instance. Any new predicate must be added to this test method as
     * well.
     */
    @Override
    public boolean test(Class<?> type) {
        return match(type.getName()) && match(type);
    }

}

