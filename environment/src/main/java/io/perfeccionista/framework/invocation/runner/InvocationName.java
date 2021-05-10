package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.ACTION;
import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.ASSERT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.CUSTOM;
import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.EMPTY;
import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.GETTER;
import static org.junit.platform.commons.util.StringUtils.isNotBlank;

public class InvocationName {

    protected final InvocationNameType type;
    protected final String format;
    protected final Object[] args;

    protected InvocationName(@NotNull InvocationNameType type, @NotNull String format, Object... args) {
        this.type = type;
        this.format = format;
        this.args = args;
    }

    public static InvocationName assertInvocation(String message, Object... args) {
        return new InvocationName(ASSERT, message, args);
    }

    public static InvocationName actionInvocation(String message, Object... args) {
        return new InvocationName(ACTION, message, args);
    }

    public static InvocationName getterInvocation(String message, Object... args) {
        return new InvocationName(GETTER, message, args);
    }

    public static InvocationName customOperationInvocation(String message, Object... args) {
        return new InvocationName(CUSTOM, message, args);
    }

    public static InvocationName empty() {
        return new InvocationName(EMPTY, "");
    }

    public boolean isNotEmpty() {
        return EMPTY != type;
    }

    public @NotNull InvocationNameType getType() {
        return type;
    }

    public @NotNull String getFormat() {
        return format;
    }

    public Object[] getArgs() {
        return args;
    }

    // TODO: Implement with bundles and arguments
    @Override
    public String toString() {
        return type + ": " + format;
    }

    public enum InvocationNameType {

        EMPTY,
        ASSERT,
        ACTION,
        GETTER,
        CUSTOM

    }

}
