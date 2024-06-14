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
     * @return приведенное значение по ключу {@param key}
     */
    <T extends V> Optional<T> get(@NotNull K key, @NotNull Class<T> clazz);

}
