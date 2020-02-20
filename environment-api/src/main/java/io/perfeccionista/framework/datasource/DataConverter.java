package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Объект, который реализует интерфейс {@link DataConverter} является
 * параметризованным конвертером данных. На вход подается значение и формат для
 * его преобразования.
 *
 * @param <K> тип ключа, передаваемого в экземпляр {@link DataConverter}
 * для получения значения
 * @param <V> тип значения, получаемого из {@link DataConverter} по передаваемому
 * ключу
 */
public interface DataConverter<K, V> {

    /**
     * Возвращает значение по переданному ключу {@param key}, используя для
     * преобразования формат {@param format}
     *
     * @param key передаваемый пользователем ключ
     * @param format формат для преобразования входящего значения {@param key}
     * @return преобразованное значение по ключу {@param key}
     */
    @NotNull V convert(@NotNull K key, @Nullable String format);

}
