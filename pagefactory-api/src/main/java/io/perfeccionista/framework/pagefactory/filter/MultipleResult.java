package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;

import java.util.Comparator;
import java.util.Map;

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
    MultipleResult<T> shouldHaveIndex(Value<Integer> indexValue);

    MultipleResult<T> shouldHaveResult(Value<T> value);

    MultipleResult<T> shouldHaveSize(Value<Integer> integerValue);

    MultipleResult<T> shouldBeSorted(Comparator<T> comparator);

}
