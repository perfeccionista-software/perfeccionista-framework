package io.perfeccionista.framework.value.checker.stringvalue;

import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.StringUtils;

public class StringEmptyValueChecker extends AbstractStringChecker {

    @Override
    public String getExpected() {
        return "";
    }

    @Override
    public String getProcessedExpected() {
        return applyTransformers("");
    }

    @Override
    public boolean check(@NotNull String actual) {
        return StringUtils.isBlank(applyTransformers(actual));
    }

}
