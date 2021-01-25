package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.FileNotExist;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_EXISTS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_NOT_EXIST;

// TODO: Привести аргументы к одному виду Path вместо Path и URL.
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

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
            logger.error(e, () -> String.format("Exception when deleting file %s", path.toString()));
        }
    }

    public static void writeBinaryFile(@NotNull Path path, byte[] raw) {
        try {
            // TODO: Проверить корректность выполнения
            Files.createFile(path);
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
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(url.getFile());
        if (!file.isFile()) {
            // TODO: Бросаем эксепшн о том, что это не файл
            throw new RuntimeException("Used path is not jsFile");
        }
        try (Stream<String> stream = Files.lines(file.toPath(), charset)) {
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        } catch (IOException e) {
            // TODO: Бросаем эксепшн о том, что не можем прочитать файл
            throw new RuntimeException("Can't read File", e);
        }
        return stringBuilder.toString();
    }

    public static Properties readPropertyFile(@NotNull String propertyFileName) {
        try (InputStream fileInputStream = new FileInputStream(propertyFileName)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            // TODO: Бросаем эксепшн о том, что не можем прочитать файл
            throw new RuntimeException("Can't read File", e);
        }
    }

    public static Optional<Properties> readOptionalPropertyFile(@NotNull String propertyFileName) {
        URL resource = FileUtils.class.getClassLoader().getResource(propertyFileName);
        if (Objects.isNull(resource)) {
            return Optional.empty();
        }
        try (InputStream fileInputStream = resource.openStream()) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return Optional.of(properties);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Map<String, String> readPropertyFileAsMap(@NotNull URL url) {
        return readPropertyFileAsMap(url, StandardCharsets.UTF_8);
    }

    public static Map<String, String> readPropertyFileAsMap(@NotNull URL url, @NotNull Charset charset) {
        return readFile(url, charset)
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

}
