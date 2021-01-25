package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.MobileShouldHaveSizeNumberMatcher;
import io.perfeccionista.framework.matcher.result.implementations.MobileShouldHaveSortedResultMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;


public class MobileMultipleResultAssertions {

    private MobileMultipleResultAssertions() {
    }


    // Size

    public static MobileShouldHaveSizeNumberMatcher haveSize(@NotNull Integer expectedSize) {
        return new MobileShouldHaveSizeNumberMatcher(expectedSize, true);
    }

    public static <T> MobileMultipleIndexedResultMatcher<T> beSorted(@NotNull Comparator<T> comparator) {
        return new MobileShouldHaveSortedResultMatcher<>(comparator);
    }

}
