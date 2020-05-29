package io.perfeccionista.framework.pagefactory.screenshots;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.LocalDateTime;

public interface Screenshot {

    LocalDateTime getCreated();

    String getName();

    String getDescription();

    String getMimeType();

    byte[] getRaw();

    Screenshot writeToFile(@NotNull Path path);

}
