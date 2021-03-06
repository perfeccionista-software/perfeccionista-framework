package io.perfeccionista.framework.assertions;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.Duration;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.utils.FileUtils.fileShouldBeMissing;
import static io.perfeccionista.framework.utils.FileUtils.fileShouldExist;

public class FileAssertions {

    public static final String FILE_EXISTS = "FileExists";
    public static final String FILE_MISSING = "FileMissing";

    private FileAssertions() {
    }

    public static void fileExists(@NotNull String filePath) {
        runCheck(assertInvocation(FILE_EXISTS), () -> fileShouldExist(Path.of(filePath)));
    }

    public static void fileExistsWithTimeout(@NotNull String filePath, @NotNull Duration duration) {
        runCheck(assertInvocation(FILE_EXISTS), () -> fileShouldExist(Path.of(filePath)), duration);
    }

    public static void fileMissing(@NotNull String filePath) {
        runCheck(assertInvocation(FILE_MISSING), () -> fileShouldBeMissing(Path.of(filePath)));
    }

    public static void fileMissingWithTimeout(@NotNull String filePath, @NotNull Duration duration) {
        runCheck(assertInvocation(FILE_MISSING), () -> fileShouldBeMissing(Path.of(filePath)), duration);
    }

}
