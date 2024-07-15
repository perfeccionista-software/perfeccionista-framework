package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ObjectDataSource<K, V> extends DataSource <K, V> {

    /**
     * Возвращает приведенное к {@param clazz} значение по переданному
     * ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @param clazz класс к которому приводится возвращаемое значение
     * @param <T> тип к которому приводится возвращаемое значение
     * @return приведенное значение по ключу {@param key} или Exception
     */
    <T extends V> T get(@NotNull K key, @NotNull Class<T> clazz);

    /**
     * Возвращает приведенное к {@param clazz} значение по переданному
     * ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @param clazz класс к которому приводится возвращаемое значение
     * @param <T> тип к которому приводится возвращаемое значение
     * @return опциональное приведенное значение по ключу {@param key}
     */
    <T extends V> Optional<T> getOptional(@NotNull K key, @NotNull Class<T> clazz);

    /**
     * Возвращает строковое представление значения, получаемого
     * по ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return строковое представление значения по ключу {@param key}
     */
    String getString(@NotNull K key);

    /**
     * Возвращает строковое представление значения, получаемого
     * по ключу {@param key}
     *
     * @param key передаваемый пользователем ключ
     * @return строковое представление значения по ключу {@param key}
     */
    Optional<String> getOptionalString(@NotNull K key);

}
