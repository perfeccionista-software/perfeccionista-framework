package io.perfeccionista.framework.value.string.checker;

import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.StringUtils;
import io.perfeccionista.framework.value.string.StringChecker;

public class StringEmptyValueChecker implements StringChecker {

    @Override
    public boolean check(@NotNull String expected, @NotNull String actual) {
        return StringUtils.isBlank(actual);
    }

    @Override
    public boolean isProcessExpectedStatement() {
        return false;
    }

}
