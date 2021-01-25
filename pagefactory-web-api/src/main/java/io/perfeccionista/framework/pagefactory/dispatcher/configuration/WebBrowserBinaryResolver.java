package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WebBrowserBinaryResolver {

    private final Map<String, Path> osDependentPaths = new HashMap<>();
    private Path defaultPath = null;

    public static WebBrowserBinaryResolver of(@NotNull String osName, @NotNull Path binaryPath) {
        return new WebBrowserBinaryResolver().addPath(osName, binaryPath);
    }

    public static WebBrowserBinaryResolver of(@NotNull Path defaultPath) {
        return new WebBrowserBinaryResolver().setDefaultPath(defaultPath);
    }

    public WebBrowserBinaryResolver setDefaultPath(@NotNull Path defaultPath) {
        this.defaultPath = defaultPath;
        return this;
    }

    public WebBrowserBinaryResolver addPath(@NotNull String osName, @NotNull Path binaryPath) {
        this.osDependentPaths.put(osName, binaryPath);
        return this;
    }

    public Optional<Path> getPath() {
        String osName = System.getProperty("os.name");
        if (osDependentPaths.containsKey(osName)) {
            return getPathForOs(osName);
        }
        return getDefaultPath();
    }

    public Optional<Path> getPathForOs(String osName) {
        return Optional.ofNullable(osDependentPaths.get(osName));
    }

    public Optional<Path> getDefaultPath() {
        return Optional.ofNullable(defaultPath);
    }

}
