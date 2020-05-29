package io.perfeccionista.framework.pagefactory.browser.configuration;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WebDriverBinaryResolver {

    private final Map<String, Path> osDependentPaths = new HashMap<>();
    private Path defaultPath = null;

    public static WebDriverBinaryResolver of(@NotNull String osName, @NotNull Path binaryPath) {
        return new WebDriverBinaryResolver().addPath(osName, binaryPath);
    }

    public static WebDriverBinaryResolver of(@NotNull Path defaultPath) {
        return new WebDriverBinaryResolver().setDefaultPath(defaultPath);
    }

    public WebDriverBinaryResolver setDefaultPath(@NotNull Path defaultPath) {
        this.defaultPath = defaultPath;
        return this;
    }

    public WebDriverBinaryResolver addPath(@NotNull String osName, @NotNull Path binaryPath) {
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
