package io.perfeccionista.framework.assertions;

import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;
import java.time.Duration;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.utils.FileUtils.fileShouldBeMissing;
import static io.perfeccionista.framework.utils.FileUtils.fileShouldExist;

public class FileAssertions {

    public static final String FILE_EXISTS = "FileExists";
    public static final String FILE_MISSING = "FileMissing";

    private FileAssertions() {
    }

    public static void assertFileExists(@NotNull String filePath) {
        MultipleAttemptInvocationWrapper.repeatInvocation(assertInvocation(FILE_EXISTS), () -> fileShouldExist(Paths.get(filePath)));
    }

    public static void assertFileExistsWithTimeout(@NotNull String filePath, @NotNull Duration duration) {
        MultipleAttemptInvocationWrapper.repeatInvocation(assertInvocation(FILE_EXISTS), () -> fileShouldExist(Paths.get(filePath)), duration);
    }

    public static void assertFileMissing(@NotNull String filePath) {
        MultipleAttemptInvocationWrapper.repeatInvocation(assertInvocation(FILE_MISSING), () -> fileShouldBeMissing(Paths.get(filePath)));
    }

    public static void assertFileMissingWithTimeout(@NotNull String filePath, @NotNull Duration duration) {
        MultipleAttemptInvocationWrapper.repeatInvocation(assertInvocation(FILE_MISSING), () -> fileShouldBeMissing(Paths.get(filePath)), duration);
    }

}
