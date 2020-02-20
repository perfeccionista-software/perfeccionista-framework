package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     * Возвращает значение по переданному ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return значение по ключу {@param key}
     */
    @Nullable V get(@NotNull K key);

    /**
     * Возвращает приведенное к {@param clazz} значение по переданному
     * ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @param clazz класс к которому приводится возвращаемое значение
     * @param <T> тип к которому приводится возвращаемое значение
     * @return приведенное значение по ключу {@param key}
     */
    @Nullable <T extends V> T get(@NotNull K key, @NotNull Class<T> clazz);

    /**
     * Возвращает строковое представление значения, получаемого
     * по ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return строковое представление значения по ключу {@param key}
     */
    @Nullable String getString(@NotNull K key);

}
