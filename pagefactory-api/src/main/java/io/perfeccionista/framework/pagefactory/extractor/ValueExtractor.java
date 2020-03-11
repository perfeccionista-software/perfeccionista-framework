package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

/**
 * Эта штука указывает путь от корневого блока до требуемого значения (блока, элемента в блоке, текста из элемента в блоке, свойства элемента и т.п.)
 * В экстрактор может передаваться фильтр, который отфильтрует блоки из которых будут извлечены значения
 * @param <V> - тип возвращаемого значения
 */
public interface ValueExtractor<T, I, V> {

    SingleResult<V> extractSingleValue(T element);

    /**
     * Если индексы пустые, то ищем по всем
     * @param element
     * @param filterResult
     * @return
     */
    MultipleResult<V> extractMultipleValues(T element, I filterResult);

}
