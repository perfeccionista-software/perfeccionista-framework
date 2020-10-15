package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.FileNotExists;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_EXISTS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_NOT_EXISTS;

// TODO: Привести аргументы к одному виду Path вместо Path и URL.
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}

    public static void shouldExist(@NotNull Path path) {
        if (!path.toFile().exists()) {
            throw FileNotExists.exception(FILE_NOT_EXISTS.getMessage(path.toString()))
                    .setProcessed(true);
        }
    }

    public static void shouldBeMissing(@NotNull Path path) {
        if (path.toFile().exists()) {
            throw FileExists.exception(FILE_EXISTS.getMessage(path.toString()))
                    .setProcessed(true);
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

    public static void writeBinaryFile(@NotNull Path path, byte[] raw) {
        try {
            Files.write(path, raw);
        } catch (IOException e) {
            // TODO: Бросаем эксепшн о том, что не можем записать файл
            e.printStackTrace();
        }
    }

    public static byte[] readBinaryFile(@NotNull Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            // TODO: Бросаем эксепшн о том, что не можем прочитать файл
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String readFile(@NotNull URL url) {
        return readFile(url, StandardCharsets.UTF_8);
    }

    public static String readFile(@NotNull URL url, @NotNull Charset charset) {
        StringBuilder scriptBuilder = new StringBuilder();
        File jsFile = new File(url.getFile());
        if (!jsFile.isFile()) {
            // TODO: Бросаем эксепшн о том, что это не файл
            throw new RuntimeException("Used path is not jsFile");
        }
        try (Stream<String> stream = Files.lines( jsFile.toPath(), charset)) {
            stream.forEach(s -> scriptBuilder.append(s).append("\n"));
        } catch (IOException e) {
            // TODO: Бросаем эксепшн о том, что не можем прочитать файл
            throw new RuntimeException("Can't read File", e);
        }
        return scriptBuilder.toString();
    }

    public static boolean isExecutable(@NotNull Path path) {
        return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
    }

}
