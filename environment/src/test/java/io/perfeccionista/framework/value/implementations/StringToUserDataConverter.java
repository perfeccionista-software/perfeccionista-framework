package io.perfeccionista.framework.value.implementations;

import io.perfeccionista.framework.datasource.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataConverter;
import io.perfeccionista.framework.name.Name;

@Name("userName to user")
public class StringToUserDataConverter implements DataConverter<String, User> {

    @Override
    public @NotNull User convert(@NotNull String key, @Nullable String format) {
        String[] nameAndSurname = key.split(" ");
        return new User(nameAndSurname[0], nameAndSurname[1]);
    }

}
