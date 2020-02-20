package io.perfeccionista.framework.pagefactory.itemextractor;

/**
 * Эта штука указывает путь от корневого блока до требуемого значения (блока, элемента в блоке, текста из элемента в блоке, свойства элемента и т.п.)
 * В экстрактор может передаваться фильтр, который отфильтрует блоки из которых будут извлечены значения
 * @param <T> - тип блока, для которого используется фильтр
 * @param <V> - тип возвращаемого значения
 */
public interface ValueExtractor<T, V> {

    IndexedItems<V> extract(Lis)


}
