package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.FileNotExist;
import io.perfeccionista.framework.exceptions.FileReadingFailed;
import io.perfeccionista.framework.exceptions.FileWritingFailed;
import io.perfeccionista.framework.exceptions.IncorrectFileName;
import io.perfeccionista.framework.exceptions.UrlReadingFailed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_READ_FILE;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_READ_URL;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_WRITE_FILE;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_EXISTS;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.FILE_NOT_EXIST;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.INCORRECT_FILE_NAME;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.TARGET_IS_NOT_A_FILE;
import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

// TODO: Привести аргументы к одному виду Path вместо Path и URL.
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final Map<String, Properties> propertiesCache = new HashMap<>();

    private FileUtils() {}

    public static @NotNull String getFileName(@NotNull String fileName) {
        checkFileName(fileName);
        int lastUnixPos = fileName.lastIndexOf(47);
        int lastWindowsPos = fileName.lastIndexOf(92);
        int index = Math.max(lastUnixPos, lastWindowsPos);
        return fileName.substring(index + 1);
    }

    public static void checkFileName(@NotNull String fileName) {
        int len = fileName.length();
        for (int i = 0; i < len; ++i) {
            if (fileName.charAt(i) == 0) {
                throw IncorrectFileName.exception(INCORRECT_FILE_NAME.getMessage(fileName));
            }
        }
    }

    public static void fileShouldExist(@NotNull Path path) {
        if (!path.toFile().exists()) {
            throw FileNotExist.assertionError(FILE_NOT_EXIST.getMessage(path.toAbsolutePath().toString()))
                    .setProcessed(true);
        }
    }

    public static void fileShouldExist(@NotNull File file) {
        if (!file.exists()) {
            throw FileNotExist.assertionError(FILE_NOT_EXIST.getMessage(file.getAbsolutePath()))
                    .setProcessed(true);
        }
    }

    public static void fileShouldBeMissing(@NotNull Path path) {
        if (path.toFile().exists()) {
            throw FileExists.assertionError(FILE_EXISTS.getMessage(path.toAbsolutePath().toString()))
                    .setProcessed(true);
        }
    }

    public static void fileShouldBeMissing(@NotNull File file) {
        if (file.exists()) {
            throw FileExists.assertionError(FILE_EXISTS.getMessage(file.getAbsolutePath()))
                    .setProcessed(true);
        }
    }

    public static boolean isFileExecutable(@NotNull Path path) {
        return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
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
            logger.error(String.format("Exception occurred when deleting file '%s'", path), e);
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
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(url.getFile());
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

    public static Properties readRequiredPropertyFileFromClasspath(@NotNull String propertyFileName) {
        if (propertiesCache.containsKey(propertyFileName)) {
            return Optional.ofNullable(propertiesCache.get(propertyFileName))
                    .orElseThrow(() -> FileNotExist.exception(FILE_NOT_EXIST.getMessage(propertyFileName)));
        }
        try (InputStreamReader fileInputStreamReader = new InputStreamReader(new FileInputStream(propertyFileName), UTF_8)) {
            Properties properties = new Properties();
            properties.load(fileInputStreamReader);
            cachePropertyFile(propertyFileName, properties);
            return properties;
        } catch (IOException e) {
            throw FileReadingFailed.exception(CANT_READ_FILE.getMessage(propertyFileName), e);
        }
    }

    public static Optional<Properties> readOptionalPropertyFileFromClasspath(@NotNull String propertyFileName) {
        if (propertiesCache.containsKey(propertyFileName)) {
            return Optional.ofNullable(propertiesCache.get(propertyFileName));
        }
        URL resource = currentThread().getContextClassLoader().getResource(propertyFileName);
        if (Objects.isNull(resource)) {
            cachePropertyFile(propertyFileName, null);
            return Optional.empty();
        }
        try (InputStreamReader fileInputStreamReader = new InputStreamReader(resource.openStream(), UTF_8)) {
            Properties properties = new Properties();
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
        return Arrays.stream(readTextFile(url, charset)
                .split(System.lineSeparator()))
                .filter(line -> line.contains("="))
                .map(line -> {
                    int delimiterIndex = line.indexOf("=");
                    return new SimpleEntry<>(line.substring(0, delimiterIndex).trim(), line.substring(delimiterIndex + 1).trim());
                }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

//    public static @NotNull Path getRequiredFileFromClasspath(@NotNull String resource) {
//        URL resourceUrl = currentThread().getContextClassLoader().getResource(resource);
//        if (Objects.isNull(resourceUrl)) {
//            throw FileNotExist.exception(RESOURCE_NOT_EXIST.getMessage(resource));
//        }
//        try {
//            URI resourceUri = resourceUrl.toURI();
//            Path fileFromResource = resourceUri.isOpaque()
//                    ? copyUrlToTemporaryFile(resourceUrl)
//                    : Paths.get(resourceUri);
//            fileShouldExist(fileFromResource);
//            return fileFromResource;
//        } catch (URISyntaxException e) {
//            throw IncorrectUrl.exception(INCORRECT_URL.getMessage(resourceUrl), e);
//        }
//    }

    public static void copyUrlToFile(@NotNull URL url, @NotNull Path path) {
        try (final InputStream inputStream = url.openStream()) {
            try (final OutputStream outputStream = new FileOutputStream(path.toFile())) {
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw FileWritingFailed.exception(CANT_WRITE_FILE.getMessage(path.toAbsolutePath().toString()), e);
            }
        } catch (IOException e) {
            throw UrlReadingFailed.exception(CANT_READ_URL.getMessage(url.toString()));
        }
    }

//    public static Path copyUrlToTemporaryFile(@NotNull URL url) {
//        try {
//            Path tempDirectory = Files.createTempDirectory("perfeccionista-upload-tmp");
//            File tempFile = File.createTempFile(getFileName(url.getFile()), ".tmp", tempDirectory.toFile());
//            copyURLToFile(url, tempFile);
//            return tempFile.toPath();
//        } catch (IOException e) {
//            throw FileWritingFailed.exception(CANT_WRITE_FILE.getMessage(getFileName(url.getFile()) + ".tmp"), e);
//        }
//    }

    private static synchronized void cachePropertyFile(@NotNull String propertyFileName, @Nullable Properties propertyFile) {
        propertiesCache.put(propertyFileName, propertyFile);
    }

}
