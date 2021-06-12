package io.perfeccionista.framework.utils.models;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import static java.util.Collections.emptyMap;

public class CloseablePath implements Closeable {

    private static final String FILE_URI_SCHEME = "file";
    private static final String JAR_URI_SCHEME = "jar";
    private static final String JAR_FILE_EXTENSION = ".jar";
    private static final String JAR_URI_SEPARATOR = "!";

    private static final Closeable NULL_CLOSEABLE = () -> {
    };

    private final Path path;
    private final Closeable delegate;

    public static CloseablePath create(URI uri) throws IOException, URISyntaxException {
        if (JAR_URI_SCHEME.equals(uri.getScheme())) {
            String[] parts = uri.toString().split(JAR_URI_SEPARATOR);
            String jarUri = parts[0];
            String jarEntry = parts[1];
            return createForJarFileSystem(new URI(jarUri), fileSystem -> fileSystem.getPath(jarEntry));
        }
        if (uri.getScheme().equals(FILE_URI_SCHEME) && uri.getPath().endsWith(JAR_FILE_EXTENSION)) {
            return createForJarFileSystem(new URI(JAR_URI_SCHEME + ':' + uri),
                    fileSystem -> fileSystem.getRootDirectories().iterator().next());
        }
        return new CloseablePath(Paths.get(uri), NULL_CLOSEABLE);
    }

    private static CloseablePath createForJarFileSystem(URI jarUri, Function<FileSystem, Path> pathProvider)
            throws IOException {
        FileSystem fileSystem = FileSystems.newFileSystem(jarUri, emptyMap());
        Path path = pathProvider.apply(fileSystem);
        return new CloseablePath(path, fileSystem);
    }

    private CloseablePath(Path path, Closeable delegate) {
        this.path = path;
        this.delegate = delegate;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

}
