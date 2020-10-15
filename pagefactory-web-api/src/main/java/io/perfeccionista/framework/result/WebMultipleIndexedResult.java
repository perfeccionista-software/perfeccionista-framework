package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

// TODO: Подумать как тут можно проверять сразу группу индексов
//  shouldHaveIndexes(intValues.containsAll(1, 2, 3, 4, 5));
// TODO: Добавить методы entryStream(), stream(), forEach() и т.п.
public interface WebMultipleIndexedResult<T, E extends WebChildElement> {

    @NotNull E getElement();

    Map<Integer, T> getValues();

    int getSize();

    WebMultipleIndexedResult<T, E> should(WebMultipleIndexedResultMatcher<T> matcher);

    @NotNull WebSingleIndexedResult<T, E> singleResult();

}