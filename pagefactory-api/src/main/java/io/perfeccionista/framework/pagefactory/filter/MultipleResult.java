package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

// TODO: Подумать как тут можно проверять сразу группу индексов
//  shouldHaveIndexes(intValues.containsAll(1, 2, 3, 4, 5));
//  Сделать MultipleValue<T> с методом checkAll(Collection<T> values)?
// TODO: Добавить методы entryStream(), stream(), forEach() и т.п.
public interface MultipleResult<T> {

    Map<Integer, T> getValues();

    SingleResult<T> singleResult();

    <R> MultipleResult<R> convert(@NotNull Function<T, R> converter);

    MultipleResult<T> shouldHaveIndex(@NotNull Value<Integer> indexValue);

    MultipleResult<T> shouldHaveResult(@NotNull Value<T> value);

    MultipleResult<T> shouldHaveSize(@NotNull Value<Integer> integerValue);

    // TODO: Для дебаг-режима выводим все полученные значения в консоль
    MultipleResult<T> shouldBeSorted(@NotNull Comparator<T> comparator);

}
