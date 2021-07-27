package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.FileNotExist;
import io.perfeccionista.framework.exceptions.FileReadingFailed;
import io.perfeccionista.framework.exceptions.FileWritingFailed;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_READ_FILE;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_WRITE_FILE;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_EXISTS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_NOT_EXIST;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.TARGET_IS_NOT_A_FILE;
import static java.nio.charset.StandardCharsets.UTF_8;

// TODO: Привести аргументы к одному виду Path вместо Path и URL.
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final Map<String, Properties> propertiesCache = new HashMap<>();

    private FileUtils() {}

    public static void fileShouldExist(@NotNull Path path) {
        if (!path.toFile().exists()) {
            throw FileNotExist.assertionError(FILE_NOT_EXIST.getMessage(path.toString()))
                    .setProcessed(true);
        }
    }

    public static void fileShouldBeMissing(@NotNull Path path) {
        if (path.toFile().exists()) {
            throw FileExists.assertionError(FILE_EXISTS.getMessage(path.toString()))
                    .setProcessed(true);
        }
    }

    public static void deleteFile(@NotNull Path path) throws IOException {
        File file = path.toFile();
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] innerFiles = file.listFiles();
            if (innerFiles != null) {
                for (File innerFile : innerFiles) {
                    deleteFile(innerFile.toPath());
                }
            }
        }
        Files.deleteIfExists(path);
    }

    public static void deleteFileIgnoreExceptions(@NotNull Path path) {
        try {
            deleteFile(path);
        } catch (IOException e) {
            logger.error(() -> String.format("Exception occurred when deleting file '%s'", path), e);
        }
    }

    public static void writeBinaryFile(@NotNull Path path, byte[] raw) {
        try {
            // TODO: Проверить корректность выполнения
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            Files.write(path, raw);
        } catch (IOException e) {
            throw FileWritingFailed.exception(CANT_WRITE_FILE.getMessage(path), e);
        }
    }

    public static byte[] readBinaryFile(@NotNull Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw FileReadingFailed.exception(CANT_READ_FILE.getMessage(path), e);
        }
    }

    public static void writeTextFile(@NotNull Path path, String content) {
        writeTextFile(path, content, UTF_8);
    }

    public static void writeTextFile(@NotNull Path path, String content, @NotNull Charset charset) {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            throw FileWritingFailed.exception(CANT_WRITE_FILE.getMessage(path), e);
        }
    }

    public static String readTextFile(@NotNull URL url) {
        return readTextFile(url, UTF_8);
    }

    public static String readTextFile(@NotNull URL url, @NotNull Charset charset) {
        var stringBuilder = new StringBuilder();
        var file = new File(url.getFile());
        if (!file.isFile()) {
            throw FileReadingFailed.exception(TARGET_IS_NOT_A_FILE.getMessage(url));
        }
        try (Stream<String> stream = Files.lines(file.toPath(), charset)) {
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        } catch (IOException e) {
            throw FileReadingFailed.exception(CANT_READ_FILE.getMessage(url), e);
        }
        return stringBuilder.toString();
    }

    public static Properties readRequiredPropertyFile(@NotNull String propertyFileName) {
        if (propertiesCache.containsKey(propertyFileName)) {
            return Optional.ofNullable(propertiesCache.get(propertyFileName))
                    .orElseThrow(() -> FileNotExist.exception(FILE_NOT_EXIST.getMessage(propertyFileName)));
        }
        try (var fileInputStreamReader = new InputStreamReader(new FileInputStream(propertyFileName), UTF_8)) {
            var properties = new Properties();
            properties.load(fileInputStreamReader);
            cachePropertyFile(propertyFileName, properties);
            return properties;
        } catch (IOException e) {
            throw FileReadingFailed.exception(CANT_READ_FILE.getMessage(propertyFileName), e);
        }
    }

    public static Optional<Properties> readOptionalPropertyFile(@NotNull String propertyFileName) {
        if (propertiesCache.containsKey(propertyFileName)) {
            return Optional.ofNullable(propertiesCache.get(propertyFileName));
        }
        URL resource = FileUtils.class.getClassLoader().getResource(propertyFileName);
        if (Objects.isNull(resource)) {
            cachePropertyFile(propertyFileName, null);
            return Optional.empty();
        }
        try (var fileInputStreamReader = new InputStreamReader(resource.openStream(), UTF_8)) {
            var properties = new Properties();
            properties.load(fileInputStreamReader);
            cachePropertyFile(propertyFileName, properties);
            return Optional.of(properties);
        } catch (IOException e) {
            cachePropertyFile(propertyFileName, null);
            return Optional.empty();
        }
    }

    public static Map<String, String> readPropertyFileAsMap(@NotNull URL url) {
        return readPropertyFileAsMap(url, UTF_8);
    }

    public static Map<String, String> readPropertyFileAsMap(@NotNull URL url, @NotNull Charset charset) {
        return readTextFile(url, charset)
                .lines()
                .filter(line -> line.contains("="))
                .map(line -> {
                    int delimiterIndex = line.indexOf("=");
                    return Map.entry(line.substring(0, delimiterIndex).trim(), line.substring(delimiterIndex + 1).trim());
                }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    public static boolean isFileExecutable(@NotNull Path path) {
        return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
    }

    private static synchronized void cachePropertyFile(@NotNull String propertyFileName, @Nullable Properties propertyFile) {
        propertiesCache.put(propertyFileName, propertyFile);
    }

}
