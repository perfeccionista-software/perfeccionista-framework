package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.ACTION;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.ASSERT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.CUSTOM;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.EMPTY;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.GETTER;

public final class InvocationInfo {

    protected final String uuid;

    protected final InvocationType type;
    protected final String format;
    protected final Object[] args;

    protected Deque<InvocationResult> invocationResults;
    protected InvocationResult current = null;

    protected InvocationInfoNameFormatter nameFormatter;
    protected InvocationInfoStatisticsFormatter statisticsFormatter;

    protected InvocationInfo(@NotNull InvocationInfo.InvocationType type, @NotNull String format, Object... args) {
        this.type = type;
        this.format = format;
        this.args = args;
        this.invocationResults = new ArrayDeque<>();
        this.nameFormatter = new DefaultInvocationInfoNameFormatter();
        this.statisticsFormatter = new DefaultInvocationInfoStatisticsFormatter();
        this.uuid = UUID.randomUUID().toString();
    }

    public static InvocationInfo assertInvocation(String message, Object... args) {
        return new InvocationInfo(ASSERT, message, args);
    }

    public static InvocationInfo actionInvocation(String message, Object... args) {
        return new InvocationInfo(ACTION, message, args);
    }

    public static InvocationInfo getterInvocation(String message, Object... args) {
        return new InvocationInfo(GETTER, message, args);
    }

    public static InvocationInfo customOperationInvocation(String message, Object... args) {
        return new InvocationInfo(CUSTOM, message, args);
    }

    public static InvocationInfo empty() {
        return new InvocationInfo(EMPTY, "");
    }

    public InvocationInfo setNameFormatter(@NotNull InvocationInfoNameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
        return this;
    }

    public InvocationInfo setStatisticsFormatter(@NotNull InvocationInfoStatisticsFormatter statisticsFormatter) {
        this.statisticsFormatter = statisticsFormatter;
        return this;
    }

    public boolean isNotEmpty() {
        return EMPTY != type;
    }

    public String getUuid() {
        return this.uuid;
    }

    public @NotNull InvocationInfo.InvocationType getType() {
        return type;
    }

    public @NotNull String getFormat() {
        return format;
    }

    public Object[] getArgs() {
        return args;
    }

    public Deque<InvocationResult> getResults() {
        return invocationResults;
    }

    public InvocationInfo start() {
        this.current = InvocationResult.start();
        return this;
    }

    public InvocationInfo start(@NotNull Consumer<InvocationInfo> startInvocationVisitor) {
        this.current = InvocationResult.start();
        startInvocationVisitor.accept(this);
        return this;
    }

    public InvocationInfo success() {
        this.current.success();
        this.invocationResults.addLast(this.current);
        this.current = null;
        return this;
    }

    public InvocationInfo success(@NotNull Consumer<InvocationInfo> successInvocationVisitor) {
        this.current.success();
        this.invocationResults.addLast(this.current);
        this.current = null;
        successInvocationVisitor.accept(this);
        return this;
    }

    public InvocationInfo exception(Throwable t) {
        this.current.exception(t);
        this.invocationResults.addLast(this.current);
        this.current = null;
        return this;
    }

    public InvocationInfo exception(Throwable t, @NotNull Consumer<InvocationInfo> exceptionInvocationVisitor) {
        this.current.exception(t);
        this.invocationResults.addLast(this.current);
        this.current = null;
        exceptionInvocationVisitor.accept(this);
        return this;
    }

    public InvocationInfo close(@NotNull Consumer<InvocationInfo> closeInvocationVisitor) {
        closeInvocationVisitor.accept(this);
        return this;
    }

    public String getFormattedName() {
        return this.nameFormatter.format(this);
    }

    public String getFormattedName(@NotNull InvocationInfoNameFormatter nameFormatter) {
        return nameFormatter.format(this);
    }

    public String getFormattedStatistics() {
        return this.statisticsFormatter.format(this);
    }

    public String getFormattedStatistics(@NotNull InvocationInfoStatisticsFormatter statisticsFormatter) {
        return statisticsFormatter.format(this);
    }

    @Override
    public String toString() {
        return nameFormatter.format(this) + " " + statisticsFormatter.format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvocationInfo that = (InvocationInfo) o;
        if (!Objects.equals(type, that.type)) {
            return false;
        }
        if (!Objects.equals(format, that.format)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(args, that.args);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + format.hashCode();
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    public enum InvocationType {

        EMPTY,
        ASSERT,
        ACTION,
        GETTER,
        CUSTOM

    }

}
