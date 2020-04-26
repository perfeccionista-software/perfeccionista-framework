package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FileExistsException;
import io.perfeccionista.framework.exceptions.FileNotExistsException;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_EXISTS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_NOT_EXISTS;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}

    public static void shouldExist(@NotNull Path path) {
        if (!path.toFile().exists()) {
            throw new FileNotExistsException(FILE_NOT_EXISTS.getMessage(path.toString()));
        }
    }

    public static void shouldBeMissing(@NotNull Path path) {
        if (path.toFile().exists()) {
            throw new FileExistsException(FILE_EXISTS.getMessage(path.toString()));
        }
    }

    public static void delete(@NotNull Path path) throws IOException {
        File file = path.toFile();
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] innerFiles = file.listFiles();
            if (innerFiles != null) {
                for (File innerFile : innerFiles) {
                    delete(innerFile.toPath());
                }
            }
        }
        Files.deleteIfExists(path);
    }

    public static void deleteIgnoreExceptions(@NotNull Path path) {
        try {
            delete(path);
        } catch (IOException e) {
            logger.error(e, () -> String.format("Exception when deleting file %s", path.toString()));
        }
    }

    public static boolean isExecutable(Path path) {
        return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
    }

}
