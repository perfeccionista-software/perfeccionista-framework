package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.matcher.result.IndexNumberMatcher;
import io.perfeccionista.framework.matcher.result.IndexNumberValueMatcher;
import io.perfeccionista.framework.matcher.result.NonNullResultMatcher;
import io.perfeccionista.framework.matcher.result.NullResultMatcher;
import io.perfeccionista.framework.matcher.result.SizeNumberMatcher;
import io.perfeccionista.framework.matcher.result.SizeNumberValueMatcher;
import io.perfeccionista.framework.matcher.result.SortMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexedResultMatcher;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class WebMultipleResultAssertions {

    private WebMultipleResultAssertions() {
    }

    // Index

    public static IndexNumberMatcher haveIndex(@NotNull Integer expectedSize) {
        return new IndexNumberMatcher(expectedSize, true);
    }

    public static IndexNumberValueMatcher haveIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new IndexNumberValueMatcher(expectedValue, true);
    }

    public static IndexNumberMatcher notHaveIndex(@NotNull Integer expectedSize) {
        return new IndexNumberMatcher(expectedSize, false);
    }

    public static IndexNumberValueMatcher notHaveIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new IndexNumberValueMatcher(expectedValue, false);
    }

    // Size

    public static SizeNumberMatcher haveSize(@NotNull Integer expectedSize) {
        return new SizeNumberMatcher(expectedSize, true);
    }

    public static SizeNumberValueMatcher haveSize(@NotNull NumberValue<Integer> expectedValue) {
        return new SizeNumberValueMatcher(expectedValue, true);
    }

    public static SizeNumberMatcher notHaveSize(@NotNull Integer expectedSize) {
        return new SizeNumberMatcher(expectedSize, false);
    }

    public static SizeNumberValueMatcher notHaveSize(@NotNull NumberValue<Integer> expectedValue) {
        return new SizeNumberValueMatcher(expectedValue, false);
    }

    // Result

    // TODO: Добавить матчеры для результатов через Value
    public static <T> WebIndexedResultMatcher<T> haveResult(T expectedResult) {
        return new WebIndexedResultMatcher<>(expectedResult);
    }

    public static <T> NonNullResultMatcher<T> haveNotNullResults() {
        return new NonNullResultMatcher<>();
    }

    public static <T> NullResultMatcher<T> haveNullResults() {
        return new NullResultMatcher<>();
    }

    // Sort

    public static <T> SortMatcher<T> beSorted(@NotNull Comparator<T> comparator) {
        return new SortMatcher<>(comparator);
    }

}
