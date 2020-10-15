package io.perfeccionista.framework.screenshots;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.LocalDateTime;

// TODO: В реализациях в методе equals() реализовать механизм сравнения скриншотов
public interface Screenshot {

    LocalDateTime getCreated();

    String getName();

    String getDescription();

    String getMimeType();

    byte[] getRaw();

    Screenshot writeToFile(@NotNull Path path);

}
