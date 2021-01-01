package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Объект, который реализует интерфейс {@link DataStorage} является параметризованным
 * хранилищем данных из которого можно запрашивать данные по ключу и сохранять.
 *
 * @param <K> тип ключа, передаваемого в экземпляр {@link DataSource}
 * для получения значения
 * @param <V> тип значения, получаемого из {@link DataSource} по передаваемому
 * ключу
 */
public interface DataStorage<K, V> extends DataSource<K, V> {

    /**
     * Метод сохраняет значение {@param additions} по ключу {@param key}. Реализация
     * данного метода должна перезаписывать значение находящееся в {@link DataStorage}
     * по ключу {@param key} если оно уже присутствует.
     *
     * @param key ключ по которому сохраняется значение {@param additions}
     * @param value значение, сохраняемое по ключу {@param key}
     */
    DataStorage<K, V> put(@NotNull K key, @Nullable V value);

    /**
     * Метод сохраняет значение {@param additions} по ключу {@param key}. Реализация
     * данного метода не должна перезаписывать значение находящееся в
     * {@link DataStorage} по ключу {@param key} если оно уже присутствует. В этом
     * случае метод должен возвращать {@link Boolean#FALSE} оставив прежнее значение.
     *
     * @param key ключ по которому сохраняется значение {@param additions}
     * @param value значение, сохраняемое по ключу {@param key}
     * @return {@link Boolean#TRUE}, если сохранение прошло,
     * {@link Boolean#FALSE} если по ключу {@param key} значение уже присутствует
     */
    boolean putIfAbsent(@NotNull K key, @Nullable V value);

    /**
     * Метод проверяет наличие ключа {@param key} в хранилище
     *
     * @param key проверяемый ключ
     * @return {@link Boolean#TRUE}, если ключ присутствует,
     * {@link Boolean#FALSE} если ключ отсутствует
     */
    boolean contains(@NotNull K key);

}
