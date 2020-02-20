package io.perfeccionista.framework.value.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataConverter;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.value.implementations.entities.User;

@Name("user to user name")
public class UserToStringDataConverter implements DataConverter<User, String> {

    @Override
    public @NotNull String convert(@NotNull User key, @Nullable String format) {
        if (null == format) {
            return key.toString();
        }
        return format + " " + key.toString();
    }

}
