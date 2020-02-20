package io.perfeccionista.framework.datasource.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SimpleDataConverter implements DataConverter<LocalDate, String> {

    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public @NotNull String convert(@NotNull LocalDate key, @Nullable String format) {
        if (null == format) {
            return defaultFormatter.format(key);
        }
        return DateTimeFormatter.ofPattern(format).format(key);
    }

}
