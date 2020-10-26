package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

import static org.junit.platform.commons.util.StringUtils.isNotBlank;

public class InvocationName {

    protected final String format;
    protected final Object[] args;

    protected InvocationName(@NotNull String format, Object... args) {
        this.format = format;
        this.args = args;
    }

    public static InvocationName of(String format, Object... args) {
        return new InvocationName(format, args);
    }

    public static InvocationName empty() {
        return new InvocationName("");
    }

    public boolean isNotEmpty() {
        return isNotBlank(format);
    }

    public String getFormat() {
        return format;
    }

    public Object[] getArgs() {
        return args;
    }

    // TODO: Implement with bundles and arguments
    @Override
    public String toString() {
        return format;
    }

}
