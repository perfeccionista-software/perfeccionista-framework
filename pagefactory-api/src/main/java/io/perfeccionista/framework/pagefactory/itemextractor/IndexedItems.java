package io.perfeccionista.framework.pagefactory.itemextractor;

import java.util.List;

public interface IndexedItems<T> {

    List<Integer> getIndexes();

    T getSingleResultOrThrow();

    List<T> getResultsOrThrow();

}
