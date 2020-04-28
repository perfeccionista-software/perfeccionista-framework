package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public interface MultipleResult<T> {

    Map<Integer, T> get();

    String getElementHash();

    static <T> MultipleResult<T> of(Map<Integer, T> result) {
        return null;
    }

    static <T> MultipleResult<T> empty() {
        return null;
    }

    // TODO: Подумать как тут можно проверять сразу группу индексов
    //  shouldHaveIndexes(intValues.containsAll(1, 2, 3, 4, 5));
    //  Сделать MultipleValue<T> с методом checkAll(Collection<T> values)?
    SingleResult<T> singleResult();

    <R> MultipleResult<R> convert(Function<T, R> converter);

    MultipleResult<T> shouldHaveIndex(Value<Integer> indexValue);

    MultipleResult<T> shouldHaveResult(Value<T> value);

    MultipleResult<T> shouldHaveSize(Value<Integer> integerValue);

    // TODO: Для дебаг-режима выводим все полученные значения в консоль
    MultipleResult<T> shouldBeSorted(Comparator<T> comparator);

}
