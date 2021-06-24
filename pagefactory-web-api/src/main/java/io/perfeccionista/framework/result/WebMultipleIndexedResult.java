package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

// TODO: Подумать как тут можно проверять сразу группу индексов
//  shouldHaveIndexes(intValues.containsAll(1, 2, 3, 4, 5));
// TODO: Добавить методы entryStream(), stream(), forEach() и т.п.
public interface WebMultipleIndexedResult<R, T extends WebChildElement> {

    @NotNull T getElement();

    Map<Integer, R> getResults();

    int getSize();

    WebMultipleIndexedResult<R, T> should(WebMultipleIndexedResultMatcher<R> matcher);

    @NotNull WebSingleIndexedResult<R, T> singleResult();

}
