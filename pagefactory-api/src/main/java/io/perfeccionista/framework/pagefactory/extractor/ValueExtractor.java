package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.filter.Filter;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;

/**
 * Эта штука указывает путь от корневого блока до требуемого значения (блока, элемента в блоке, текста из элемента в блоке, свойства элемента и т.п.)
 * В экстрактор может передаваться фильтр, который отфильтрует блоки из которых будут извлечены значения
 * @param <V> - тип возвращаемого значения
 */
public interface ValueExtractor<T extends Element, F extends Filter<T, ?>, V> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    MultipleResult<V> extractValues(T element, F filter);

}
