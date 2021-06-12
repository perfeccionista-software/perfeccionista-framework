package io.perfeccionista.framework.utils.models;

import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.ExceptionUtils.throwIfUnhandled;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.*;
import static io.perfeccionista.framework.utils.models.ClassFileVisitor.CLASS_FILE_SUFFIX;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ClasspathScanner {
    private static final Logger logger = LoggerFactory.getLogger(ClasspathScanner.class);

    private static final char CLASSPATH_RESOURCE_PATH_SEPARATOR = '/';
    private static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final String DEFAULT_PACKAGE_NAME = "";
    private static final String PACKAGE_SEPARATOR_STRING = String.valueOf(PACKAGE_SEPARATOR_CHAR);
    private static final String MALFORMED_CLASS_NAME_ERROR_MESSAGE = "Malformed class name";
    private static final Pattern DOT_PATTERN = Pattern.compile("\\.");
    private static final Set<String> RESTRICTED_KEYWORDS = Set.of("_",
            "abstract", "assert", "break", "case", "catch", "const", "continue", "default", "do", "else", "enum", "false", "final", "finally",
            "for", "goto", "if", "import", "instanceof", "native", "new", "null", "private", "protected", "public", "return", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "transient", "true", "try", "volatile", "while");

    private final ClassLoader classLoader;

    public ClasspathScanner() {
        this.classLoader = getDefaultClassLoader();
    }

    public ClasspathScanner(@NotNull ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public List<Class<?>> scanForClassesInPackage(@NotNull String basePackageName, @NotNull ClassFilter classFilter) {
        assertPackageNameIsValid(basePackageName);
        basePackageName = basePackageName.trim();
        return findClassesForUris(basePackageName, classFilter);
    }

    public List<Class<?>> scanForClassesInClasspathRoot(@NotNull URI root, @NotNull ClassFilter classFilter) {
        return findClassesForUri(root, DEFAULT_PACKAGE_NAME, classFilter);
    }

    public List<Class<?>> findClassesForUris(String basePackageName, ClassFilter classFilter) {
        List<URI> baseUris = getRootUrisForPackage(basePackageName);
        // @formatter:off
        return baseUris.stream()
                .map(baseUri -> findClassesForUri(baseUri, basePackageName, classFilter))
                .flatMap(Collection::stream)
                .distinct()
                .collect(toList());
        // @formatter:on
    }

    public List<Class<?>> findClassesForUri(URI baseUri, String basePackageName, ClassFilter classFilter) {
        try (CloseablePath closeablePath = CloseablePath.create(baseUri)) {
            Path baseDir = closeablePath.getPath();
            return findClassesForPath(baseDir, basePackageName, classFilter);
        } catch (IOException | URISyntaxException e) {
            logger.warn(() -> String.format("Error scanning files for URI '%s'", baseUri), e);
            return emptyList();
        }
    }

    public List<Class<?>> findClassesForPath(Path baseDir, String basePackageName, ClassFilter classFilter) {
        if (!Files.exists(baseDir)) {
            throw PreconditionViolation.exception("baseDir '" + baseDir + "' must exist");
        }
        List<Class<?>> classes = new ArrayList<>();
        try {
            Files.walkFileTree(baseDir, new ClassFileVisitor(
                    classFile -> processClassFileSafely(baseDir, basePackageName, classFilter, classFile, classes::add)));
        }
        catch (IOException e) {
            logger.warn(() -> String.format("I/O error scanning files in '%s'", baseDir), e);
        }
        return classes;
    }

    public List<URI> getRootUrisForPackage(String basePackageName) {
        try {
            Enumeration<URL> resources = classLoader.getResources(packagePath(basePackageName));
            List<URI> uris = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                uris.add(resource.toURI());
            }
            return uris;
        }
        catch (Exception e) {
            logger.warn(() -> String.format("Error reading URIs from class loader for base package '%s'", basePackageName), e);
            return emptyList();
        }
    }

    private void processClassFileSafely(Path baseDir, String basePackageName, ClassFilter classFilter, Path classFile,
                                        Consumer<Class<?>> classConsumer) {
        try {
            String fullyQualifiedClassName = determineFullyQualifiedClassName(baseDir, basePackageName, classFile);
            if (classFilter.match(fullyQualifiedClassName)) {
                try {
                    Optional.of(loadClass(fullyQualifiedClassName, classLoader))
                            .filter(classFilter)
                            .ifPresent(classConsumer);
                } catch (InternalError internalError) {
                    if (MALFORMED_CLASS_NAME_ERROR_MESSAGE.equals(internalError.getMessage())) {
                        logger.debug(() -> String.format("The class loaded from path '%s' has a malformed class name '%s'", classFile, fullyQualifiedClassName));
                    }
                    logger.debug(() -> String.format("Failed to load class by path '%s'", classFile));
                }
            }
        } catch (Throwable t) {
            throwIfUnhandled(t);
            logger.debug(() -> String.format("Failed to load class by path '%s'", classFile));
        }
    }

    private static String packagePath(String packageName) {
        if (packageName.isEmpty()) {
            return "";
        }
        String path = packageName.replace(PACKAGE_SEPARATOR_CHAR, CLASSPATH_RESOURCE_PATH_SEPARATOR);
        return path + CLASSPATH_RESOURCE_PATH_SEPARATOR;
    }

    private String determineFullyQualifiedClassName(Path baseDir, String basePackageName, Path classFile) {
        // @formatter:off
        return Stream.of(
                basePackageName,
                determineSubpackageName(baseDir, classFile),
                determineSimpleClassName(classFile)
        )
                .filter(value -> !value.isEmpty()) // Handle default package appropriately.
                .collect(joining(PACKAGE_SEPARATOR_STRING));
        // @formatter:on
    }

    private String determineSimpleClassName(Path classFile) {
        String fileName = classFile.getFileName().toString();
        return fileName.substring(0, fileName.length() - CLASS_FILE_SUFFIX.length());
    }

    private String determineSubpackageName(Path baseDir, Path classFile) {
        Path relativePath = baseDir.relativize(classFile.getParent());
        String pathSeparator = baseDir.getFileSystem().getSeparator();
        String subpackageName = relativePath.toString().replace(pathSeparator, PACKAGE_SEPARATOR_STRING);
        if (subpackageName.endsWith(pathSeparator)) {
            // Workaround for JDK bug: https://bugs.openjdk.java.net/browse/JDK-8153248
            subpackageName = subpackageName.substring(0, subpackageName.length() - pathSeparator.length());
        }
        return subpackageName;
    }

    private static void assertPackageNameIsValid(@NotNull String packageName) {
        if (StringUtils.isBlank(packageName)) {
            return;
        }
        boolean allValid = Arrays.stream(DOT_PATTERN.split(packageName, -1))
                .allMatch(ClasspathScanner::isJavaName);
        if (allValid) {
            return;
        }
        throw PreconditionViolation.exception("Invalid part in package name '" + packageName + "'");
    }

    private static boolean isJavaName(String name) {
        return name != null && !name.isEmpty() && isNotRestrictedKeyword(name) && isJavaIdentifier(name);
    }

    private static boolean isNotRestrictedKeyword(String name) {
        return !RESTRICTED_KEYWORDS.contains(name);
    }

    private static boolean isJavaIdentifier(String name) {
        int start = name.codePointAt(0);
        if (!Character.isJavaIdentifierStart(start)) {
            return false;
        }
        int charCount = Character.charCount(start);
        for (int i = charCount; i < name.length(); i += charCount) {
            int codePoint = name.codePointAt(i);
            if (!Character.isJavaIdentifierPart(codePoint)) {
                return false;
            }
        }
        return true;
    }
}

