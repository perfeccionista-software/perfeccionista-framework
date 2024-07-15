package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Объект, который реализует интерфейс {@link DataSource} является параметризованным
 * источником данных.
 *
 * {@link DataSource} может быть:
 * <UL>
 *     <li>источник данных</li>
 *     <li>генератор данных, возвращающий какое-то значение по переданному ключу</li>
 *     <li>преобразователь данных, который получает на вход исходные данные,
 *     а возвращает преобразованные</li>
 * </UL>
 *
 * @param <K> тип ключа, передаваемого в экземпляр {@link DataSource}
 * для получения значения
 * @param <V> тип значения, получаемого из {@link DataSource} по передаваемому
 * ключу
 */
public interface DataSource<K, V> {

    /**
     * Метод проверяет наличие ключа {@param key} в хранилище
     *
     * @param key проверяемый ключ
     * @return {@link Boolean#TRUE}, если ключ присутствует,
     * {@link Boolean#FALSE} если ключ отсутствует
     */
    boolean contains(@NotNull K key);

    /**
     * Возвращает значение по переданному ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return Значение по ключу {@param key} или Exception
     */
    V get(@NotNull K key);

    /**
     * Возвращает значение по переданному ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return Опциональное значение по ключу {@param key}
     */
    Optional<V> getOptional(@NotNull K key);

}
